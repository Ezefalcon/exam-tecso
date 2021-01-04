export class UserSignupModel {

  username: string;
  password: string;
  email: string;

  constructor(username, password, email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  static fromForm({username, password, email}) {
    return new UserSignupModel(username, password, email);
  }
}
