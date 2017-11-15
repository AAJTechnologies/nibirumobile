package org.nibiru.mobile.core.impl.preferences;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;

import java.util.Date;

import javax.annotation.Nullable;


public abstract class AbstractPreferences extends
        BaseConfigurable<Preferences> implements Preferences {

    private enum Type {
        STRING {
            @Override
            boolean accept(Object object) {
                return object instanceof String;
            }

            @Override
            Object asObject(String data) {
                return data;
            }
        },
        CHARACTER {
            @Override
            boolean accept(Object object) {
                return object instanceof Character;
            }

            @Override
            Object asObject(String data) {
                return data.charAt(0);
            }
        },
        BYTE {
            @Override
            boolean accept(Object object) {
                return object instanceof Byte;
            }

            @Override
            Object asObject(String data) {
                return Byte.valueOf(data);
            }
        },
        SHORT {
            @Override
            boolean accept(Object object) {
                return object instanceof Short;
            }

            @Override
            Object asObject(String data) {
                return Short.valueOf(data);
            }
        },
        INTEGER {
            @Override
            boolean accept(Object object) {
                return object instanceof Integer;
            }

            @Override
            Object asObject(String data) {
                return Integer.valueOf(data);
            }
        },
        LONG {
            @Override
            boolean accept(Object object) {
                return object instanceof Long;
            }

            @Override
            Object asObject(String data) {
                return Long.valueOf(data);
            }
        },
        FLOAT {
            @Override
            boolean accept(Object object) {
                return object instanceof Float;
            }

            @Override
            Object asObject(String data) {
                return Float.valueOf(data);
            }
        },
        DOUBLE {
            @Override
            boolean accept(Object object) {
                return object instanceof Double;
            }

            @Override
            Object asObject(String data) {
                return Double.valueOf(data);
            }
        },
        BOOLEAN {
            @Override
            boolean accept(Object object) {
                return object instanceof Boolean;
            }

            @Override
            Object asObject(String data) {
                return Boolean.valueOf(data);
            }
        }, DATE {
            @Override
            boolean accept(Object object) {
                return object instanceof Date;
            }

            @Override
            Object asObject(String data) {
                return new Date(Long.parseLong(data));
            }

            @Override
            String asString(Object object) {
                return String.valueOf(((Date) object).getTime());
            }
        };

        abstract boolean accept(Object object);

        abstract <T> T asObject(String data);

        String asString(Object object) {
            return object.toString();
        }
    }


    private static final char SEPARATOR = ':';

    protected <T> T stringToObject(@Nullable String data) {
        if (data != null && data.length() > 0) {
            int separator = data.indexOf(SEPARATOR);
            if (separator >= 0) {
                return Type.valueOf(data.substring(0, separator))
                        .asObject(data.substring(separator + 1));
            } else {
                throw new IllegalArgumentException("Invalid data: " + data);
            }
        } else {
            return null;
        }
    }

    protected String objectToString(@Nullable Object object) {
        if (object != null) {
            for (Type type : Type.values()) {
                if (type.accept(object)) {
                    return type.toString() + SEPARATOR + type.asString(object);
                }
            }
            throw new IllegalArgumentException("Invalid object type: "
                    + object.getClass());
        } else {
            return null;
        }
    }
}
