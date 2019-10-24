/**
 * 
 */
package entidades;

/**
 * @author usuario
 *
 */
public class Libro {
	
	private String ISBN;
	private long IDAUTOR;
	private String NOMBREAUTOR;
	private String TITULO;
	private long TOTALES;
	private long PRESTADOS;
	private long DISPONIBLES;
	
	
	public Libro(){
	}


	public Libro(String iSBN, long iDAUTOR, String NOMBREAUTOR, String TITULO, long TOTALES, long PRESTADOS,
			long DISPONIBLES) {
		super();
		
		ISBN = iSBN;
		this.IDAUTOR = iDAUTOR;
		this.NOMBREAUTOR = NOMBREAUTOR;
		this.TITULO = TITULO;
		this.TOTALES = TOTALES;
		this.PRESTADOS = PRESTADOS;
		this.DISPONIBLES = DISPONIBLES;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public long getIDAUTOR() {
		return IDAUTOR;
	}


	public void setIDAUTOR(long iDAUTOR) {
		this.IDAUTOR = iDAUTOR;
	}


	public String getNOMBREAUTOR() {
		return NOMBREAUTOR;
	}


	public void setNOMBREAUTOR(String NOMBREAUTOR) {
		this.NOMBREAUTOR = NOMBREAUTOR;
	}


	public String getTITULO() {
		return TITULO;
	}


	public void setTITULO(String TITULO) {
		this.TITULO = TITULO;
	}


	public long getTOTALES() {
		return TOTALES;
	}


	public void setTOTALES(long TOTALES) {
		this.TOTALES = TOTALES;
	}


	public long getPRESTADOS() {
		return PRESTADOS;
	}


	public void setPRESTADOS(long PRESTADOS) {
		this.PRESTADOS = PRESTADOS;
	}


	public long getDISPONIBLES() {
		return DISPONIBLES;
	}


	public void setDISPONIBLES(long DISPONIBLES) {
		this.DISPONIBLES = DISPONIBLES;
	}


	@Override
	public String toString() {
		return "libro [ISBN=" + ISBN + ", IDAUTOR=" + IDAUTOR + ", NOMBREAUTOR=" + NOMBREAUTOR + ", TITULO=" + TITULO
				+ ", TOTALES=" + TOTALES + ", PRESTADOS=" + PRESTADOS + ", DISPONIBLES=" + DISPONIBLES + "]";
	}
	
}	