package in.shakthi.exception;

public class PatientNotFoundException extends RuntimeException{
	public static final long serialVersionUID=1L;
	public PatientNotFoundException() {
	  }

	  public PatientNotFoundException(String message) {
	    super(message);
	  }

}
