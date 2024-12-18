package in.shakthi.exception;

public class SpecializationNotFoundException extends RuntimeException 
{

	public static final long serialVersionUID=1L;
	
	public SpecializationNotFoundException()
	{
		super();
	}
	
	public SpecializationNotFoundException(String message)
	{
		super(message);
	}
}
