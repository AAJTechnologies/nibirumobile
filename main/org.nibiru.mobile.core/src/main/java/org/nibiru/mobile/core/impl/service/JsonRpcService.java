package org.nibiru.mobile.core.impl.service;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.serializer.Serializer;

public class JsonRpcService extends BaseService {
    public JsonRpcService(String serviceName, HttpManager httpManager,
                          Serializer serializer) {
        super(serviceName, httpManager, serializer);
    }

    @Override
    public <T> Promise<T, HttpException> invoke(String method,
                                                @Nullable Object requestDto,
                                                Class<T> responseClass) {
        checkNotNull(method);
        checkNotNull(requestDto);
        checkNotNull(responseClass);

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

        return getHttpManager()
                .send(getServiceName(), request.toString())
                .map(parse(responseClass));
    }

    private String extractResult(String responseMessage) {
        // TODO: no seria mas simple hacer una abstraccion de un parser de JSON?
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
