export class User {
    public userName: string;
    public password: string;
    public role: string;
    public email: string;

    constructor(username, password, role, email) {
        this.userName = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }
  
}