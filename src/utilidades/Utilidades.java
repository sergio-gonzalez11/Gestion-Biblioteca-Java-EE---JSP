package utilidades;

public class Utilidades {

	public Utilidades() {}

	public static String eliminarAcentos(String original) {
		final String ORIGINAL = "ÁáÉéÍíÓóÚúÜüÀàÈèÌìÒòÙù";
        final String REEMPLAZO = "AaEeIiOoUuUuAaEeIiOoUu";
        if (original == null) {
            return null;
        }
        char[] array = original.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int pos = ORIGINAL.indexOf(array[i]);
            if (pos > -1) {
                array[i] = REEMPLAZO.charAt(pos);
            }
        }
        return new String(array);
    }
}
