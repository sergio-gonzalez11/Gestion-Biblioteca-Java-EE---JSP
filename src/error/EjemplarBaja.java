/**
 * 
 */
package error;

/**
 * @author usuario
 *
 */
public class EjemplarBaja extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Ejemplar dado de baja o no existe dicho ejemplar";
	}

}


