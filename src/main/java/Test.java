import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;

public class Test
{
	public static void main(String[] args)
	{
		Resource resource = new ClassResource("images/green-arrow.png");
		System.out.println(resource);
	}
}
