package org.nibiru.mobile.core.impl.service;

import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpRequest.Builder;
import org.nibiru.mobile.core.api.http.HttpResponse;
import org.nibiru.mobile.core.api.serializer.Serializer;

import javax.annotation.Nullable;

public class JsonRpcService extends BaseService {
    public JsonRpcService(String baseUrl,
                          String serviceName,
                          HttpManager httpManager,
                          Serializer serializer) {
        super(baseUrl, serviceName, httpManager, serializer);
    }

    @Override
    public Builder requestBuilder(String method,
                                  @Nullable Object requestDto) {
        StringBuilder request = new StringBuilder();
        request.append("{\"id\":1,\"jsonrpc\":\"jsonrpc\",\"method\":\"");
        request.append(method);
        request.append("\"");
        if (requestDto != null) {
            request.append(",\"params\":[");
            request.append(getSerializer().serialize(requestDto));
            request.append("]");
        }
        request.append("}");

        return HttpRequest.builder(getBaseUrl() + getServiceName())
                .body(request.toString());
    }

    @Override
    protected String extractResult(HttpResponse response) {
        // TODO: A JSON parser abstraction woudl be helpful?
        String responseMessage = response.getBody();
        String parameter = "\"result\":";
        int start = responseMessage.indexOf(parameter);

        if (start >= 0) {
            start += parameter.length();
            int end = start;
            int nesting = 0;
            char currentChar = responseMessage.charAt(end);
            while (end <= responseMessage.length()
                    && (nesting > 0 || (currentChar != ',' && currentChar != '}'))) {

                if (currentChar == '{' || currentChar == '[') {
                    nesting++;
                } else if (currentChar == '}' || currentChar == ']') {
                    nesting--;
                }
                end++;
                if (end <= responseMessage.length()) {
                    currentChar = responseMessage.charAt(end);
                } else {
                    currentChar = ' ';
                }
            }
            return responseMessage.substring(start, end);

        } else {
            return "";
        }
    }
}

