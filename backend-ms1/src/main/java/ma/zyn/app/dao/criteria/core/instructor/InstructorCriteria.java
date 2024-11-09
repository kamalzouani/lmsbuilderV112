package  ma.zyn.app.dao.criteria.core.instructor;



import ma.zyn.app.zynerator.security.dao.criteria.core.UserCriteria;

import java.util.List;

public class InstructorCriteria extends UserCriteria  {

    private String bio;
    private String bioLike;
    private String expertise;
    private String expertiseLike;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private String email;
    private String emailLike;
    private String password;
    private String passwordLike;
    private Boolean accountNonLocked;
    private Boolean passwordChanged;
    private String username;
    private String usernameLike;
    private Boolean accountNonExpired;



    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }
    public String getBioLike(){
        return this.bioLike;
    }
    public void setBioLike(String bioLike){
        this.bioLike = bioLike;
    }

    public String getExpertise(){
        return this.expertise;
    }
    public void setExpertise(String expertise){
        this.expertise = expertise;
    }
    public String getExpertiseLike(){
        return this.expertiseLike;
    }
    public void setExpertiseLike(String expertiseLike){
        this.expertiseLike = expertiseLike;
    }

    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }

    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }

}
