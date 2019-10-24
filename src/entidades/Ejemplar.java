/**
 * 
 */
package entidades;

/**
 * @author usuario
 *
 */
	public class Ejemplar {
		
		private long IDEJEMPLAR;
		private String ISBN;
		private char BAJA;
		
		
		public Ejemplar() {
			}

		
		public Ejemplar(long iDEJEMPLAR, String iSBN, char bAJA) {
			super();
			IDEJEMPLAR = iDEJEMPLAR;
			ISBN = iSBN;
			BAJA = bAJA;
		}


		public long getIDEJEMPLAR() {
			return IDEJEMPLAR;
		}


		public void setIDEJEMPLAR(long iDEJEMPLAR) {
			IDEJEMPLAR = iDEJEMPLAR;
		}


		public String getISBN() {
			return ISBN;
		}


		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}


		public void setBAJA(char bAJA) {
			BAJA = bAJA;
		}


		public char getBAJA() {
			return BAJA;
		}


		@Override
		public String toString() {
			return "ejemplar [IDEJEMPLAR=" + IDEJEMPLAR + ", ISBN=" + ISBN + ", BAJA=" + BAJA + "]";
		}	
		
	}
