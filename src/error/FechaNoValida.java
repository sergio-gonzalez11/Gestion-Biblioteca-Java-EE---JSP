/**
 * 
 */
package error;

/**
 * @author usuario
 *
 */
public class FechaNoValida extends Exception {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Formato de fecha no válida! introduce dd-MM-yy";
	}

}
