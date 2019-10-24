package entidades;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Autor {
	
	private int IDAUTOR;
	private String NOMBRE;
	private Date FECHANACIMIENTO;
	

	public Autor(){
	}

	
	public Autor(int iDAUTOR, String nOMBRE, Date fECHANACIMIENTO) {
		super();
		IDAUTOR = iDAUTOR;
		NOMBRE = nOMBRE;
		FECHANACIMIENTO = fECHANACIMIENTO;
	}


	public void setIDAUTOR(int IDAUTOR) {
		this.IDAUTOR = IDAUTOR;
	}
	
	public int getIDAUTOR() {
		return IDAUTOR;
	}
	
	public void setNOMBRE(String NOMBRE) {
		this.NOMBRE = NOMBRE;
	}
	
	public String getNOMBRE() {
		return NOMBRE;
	}
	
	public void setFECHANACIMIENTO(Date FECHANACIMIENTO) {
		this.FECHANACIMIENTO = FECHANACIMIENTO;
	}
	
	public Date getFECHANACIMIENTO() {
		return FECHANACIMIENTO;
	}
	
	public String getFechaNacimientoFormateada(){
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
		String fechaFormateada=formatoDeFecha.format((java.util.Date)FECHANACIMIENTO);
		return fechaFormateada;
	}

	@Override
	public String toString() {
		return "Autor [IDAUTOR=" + IDAUTOR + ", NOMBRE=" + NOMBRE + ", FECHANACIMIENTO=" + FECHANACIMIENTO + "]";
	}
	
	
	
}
