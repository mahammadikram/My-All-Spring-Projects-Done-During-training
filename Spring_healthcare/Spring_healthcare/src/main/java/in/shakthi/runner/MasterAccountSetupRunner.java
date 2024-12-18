package in.shakthi.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.shakthi.constants.UserRoles;
import in.shakthi.entity.User;
import in.shakthi.services.IUserService;
import in.shakthi.util.UserUtil;

@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
//	@Autowired
//	private MyMailUtil mailUtil ;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			//user.setPassword(userUtil.genPwd());
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			userService.saveUser(user);
			//TODO : EMAIL SERVICE
			
			Long genId  = userService.saveUser(user);
//			if(genId!=null)
//				new Thread(new Runnable() {
//					public void run() {
//						String text = "Your uname is " + username +", password is "+ pwd;
//						mailUtil.send(username, "ADMIN ADDED", text);
//					}
//				}).start();		
			
		}
		
	}

}
