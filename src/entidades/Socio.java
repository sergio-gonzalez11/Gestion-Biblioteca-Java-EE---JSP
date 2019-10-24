/**
 * 
 */
package entidades;

/**
 * @author usuario
 *
 */
public class Socio {
	
	private int IDSOCIO;
	private String EMAIL;
	private String NOMBRE;
	private String DIRECCION;
	private String CLAVE;
	
	
	
	
	public Socio() {
		super();
	}


	public Socio(int iDSOCIO, String eMAIL, String nOMBRE, String dIRECCION, String CLAVE) {
		super();
		IDSOCIO = iDSOCIO;
		EMAIL = eMAIL;
		NOMBRE = nOMBRE;
		DIRECCION = dIRECCION;
		this.CLAVE=CLAVE;
	}


	public int getIDSOCIO() {
		return IDSOCIO;
	}
	
	public void setIDSOCIO(int iDSOCIO) {
		IDSOCIO = iDSOCIO;
	}
	
	public String getEMAIL() {
		return EMAIL;
	}
	
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	
	public String getNOMBRE() {
		return NOMBRE;
	}
	
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	
	public String getDIRECCION() {
		return DIRECCION;
	}
	
	public void setDIRECCION(String dIRECCION) {
		DIRECCION = dIRECCION;
	}

	
	public String getCLAVE() {
		return CLAVE;
	}


	public void setCLAVE(String CLAVE) {
		this.CLAVE = CLAVE;
	}


	@Override
	public String toString() {
		return "socio [IDSOCIO=" + IDSOCIO + ", EMAIL=" + EMAIL + ", NOMBRE=" + NOMBRE + ", DIRECCION=" + DIRECCION
				+ ", CLAVE=" + CLAVE + "]";
	}

}
