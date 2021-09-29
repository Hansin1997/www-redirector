package cn.dustlight.wwwredirector.core;

public class DefaultRedirector implements Redirector{

    private static final String SCHEMA_SPLIT = "://";
    public static final String WWW = "www.";

    @Override
    public String computeRedirect(String origin) {
        int index = origin.indexOf(SCHEMA_SPLIT);
        if (index != -1) {
            String schema = origin.substring(0, index);
            String suffix = origin.substring(index + SCHEMA_SPLIT.length());
            if (!suffix.startsWith(WWW)) {
                return String.format("%s%s%s%s", schema, SCHEMA_SPLIT, WWW, suffix);
            }
        }
        return null;
    }
}
