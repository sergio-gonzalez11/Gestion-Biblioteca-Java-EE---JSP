/**
 * 
 */
package entidades;

import java.util.Date;

/**
 * @author usuario
 *
 */
	public class Prestamo {
		
		private int IDEJEMPLAR;
		private long IDSOCIO;
		private Date FECHAPRESTAMO;
		private Date FECHALIMITEDEVOLCUION;
		
		
		public Prestamo() {
			super();
		}

		
		public Prestamo(int iDEJEMPLAR, long iDSOCIO) {
			super();
			IDEJEMPLAR = iDEJEMPLAR;
			IDSOCIO = iDSOCIO;
			
		}

		public int getIDEJEMPLAR() {
			return IDEJEMPLAR;
		}
		
		public void setIDEJEMPLAR(int iDEJEMPLAR) {
			IDEJEMPLAR = iDEJEMPLAR;
		}
		
		
		public long getIDSOCIO() {
			return IDSOCIO;
		}
		
		public void setIDSOCIO(long iDSOCIO) {
			IDSOCIO = iDSOCIO;
		}
		
		public Date getFECHAPRESTAMO() {
			return FECHAPRESTAMO;
		}
		
		public void setFECHAPRESTAMO(Date fECHAPRESTAMO) {
			FECHAPRESTAMO = fECHAPRESTAMO;
		}
		
		public Date getFECHALIMITEDEVOLCUION() {
			return FECHALIMITEDEVOLCUION;
		}
		
		public void setFECHALIMITEDEVOLCUION(Date fECHALIMITEDEVOLCUION) {
			FECHALIMITEDEVOLCUION = fECHALIMITEDEVOLCUION;
		}


		@Override
		public String toString() {
			return "Prestamo [IDEJEMPLAR=" + IDEJEMPLAR + ", IDSOCIO=" + IDSOCIO + ", FECHAPRESTAMO=" + FECHAPRESTAMO
					+ ", FECHALIMITEDEVOLCUION=" + FECHALIMITEDEVOLCUION + "]";
		}
		
		
	}
	
	