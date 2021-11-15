package server;

public class Cookie{
	public String username;
	public enum Role{normal_user,admin}
	public Role role;
	public Cookie() {
		username = "Cookie";
		role = Role.normal_user;
	}
	
	//a cookie is at least 5 bytes big
	//a binary cookie's last 3 bytes are used to identify it as a cookie
	//the rest is used to decode its values
	public Cookie(byte[] data) {
		int size = data.length;
		assert(size > 4);
		assert(data[size-3]==(byte)'c' && data[size-2]==(byte)'o' && data[size-1]==(byte)'o');
		int stringsize = (size - 4);
		byte[] name = new byte[stringsize];
		for(int i = 0; i < stringsize; i++) {
			name[i] = data[i];
		}
		
		this.username = new String(name);
		
		if(data[size - 4] == (byte)'1') {
			this.role = Role.admin;
		} else {
			this.role = Role.normal_user;
		}
	}
}