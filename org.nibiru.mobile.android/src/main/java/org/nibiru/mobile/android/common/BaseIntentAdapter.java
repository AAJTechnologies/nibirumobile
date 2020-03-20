package org.nibiru.mobile.android.common;

import android.content.Intent;

import org.nibiru.mobile.core.api.common.Configurable;
import org.nibiru.mobile.core.api.common.Identifiable;

import java.io.Serializable;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseIntentAdapter<T>
        implements Configurable<T, Serializable>,
        Identifiable<String> {
    public final static String PREFIX = "org.nibiru.mobile.";
    private final String ID_KEY = "nibiruId";
    private final Intent intent;

    public BaseIntentAdapter(Intent intent) {
        this.intent = checkNotNull(intent);
    }

    public BaseIntentAdapter(String id,
                             Intent intent) {
        this(intent);
        this.intent.putExtra(ID_KEY, checkNotNull(id));
    }

    @Override
    public String getId() {
        return intent.getExtras().getString(ID_KEY);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K extends Serializable> K getParameter(String key) {
        checkNotNull(key);
        return (K) intent.getExtras().get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T addParameter(String key,
                          @Nullable Serializable value) {
        checkNotNull(key);
        intent.putExtra(key, (Serializable) value);
        return (T) this;
    }

    protected Intent getIntent() {
        return intent;
    }
}
