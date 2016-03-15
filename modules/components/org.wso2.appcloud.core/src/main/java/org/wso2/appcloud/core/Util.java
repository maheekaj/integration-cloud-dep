package org.wso2.appcloud.core;


public class Util {

    private static final long[] byteTable = createLookupTable();
    private static final long HSTART = 0xBB40E64DA205B064L;
    private static final long HMULT = 7664345821815920749L;

    public static long hash(byte[] data) {
        long h = HSTART;
        final long hmult = HMULT;
        final long[] ht = byteTable;
        for (int len = data.length, i = 0; i < len; i++) {
            h = (h * hmult) ^ ht[data[i] & 0xff];
        }
        return h;
    }

    private static final long[] createLookupTable() {
        long[] byteTable = new long[256];
        long h = 0x544B2FBACAAF1684L;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 31; j++) {
                h = (h >>> 7) ^ h;
                h = (h << 11) ^ h;
                h = (h >>> 10) ^ h;
            }
            byteTable[i] = h;
        }
        return byteTable;
    }

    public static long hash(CharSequence cs) {
        long h = HSTART;
        final long hmult = HMULT;
        final long[] ht = byteTable;
        final int len = cs.length();
        for (int i = 0; i < len; i++) {
            char ch = cs.charAt(i);
            h = (h * hmult) ^ ht[ch & 0xff];
            h = (h * hmult) ^ ht[(ch >>> 8) & 0xff];
        }
        return h < 0 ? h*-1 : h;
    }

    public static String getVersionHashId (String applicationName, String versionName, int tenantId){
        String idString = tenantId + applicationName + versionName;
        return Long.toString(Util.hash(idString));
    }

    public static String getApplicationHashId (String applicationName, int tenantId){
        String idString = tenantId + applicationName;
        return Long.toString(Util.hash(idString));
    }

    public static String getRuntimeValidAppName(String applicationName){
        applicationName = applicationName.replaceAll("[^a-zA-Z0-9]+", "-");
        return applicationName;
    }
}