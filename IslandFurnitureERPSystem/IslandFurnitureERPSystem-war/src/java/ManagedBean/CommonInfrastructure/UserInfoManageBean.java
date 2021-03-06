/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.UserEntity;
import SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import util.security.CryptographicHelper;

/**
 *
 * @author dan
 */
@Named(value = "userInfo")
@ViewScoped
public class UserInfoManageBean implements Serializable {

    @EJB
    private InternalUserAccountManagementModuleLocal IUMA;

    private String userId;
    private String FirstName;
    private String MidName;
    private String LastName;
    private String Title;
    private String Gender;
    private String Position;
    private String Department;
    private String Email;
    private String Address;
    private String Postal;
    private String password;

    private Integer userLevel;
    private Calendar birthday;
    private long departmentId;

    private String birString;// used to convert birthday between string and calendar
    private String inputOldPass;
    private String newPass;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

    /**
     * Creates a new instance of UserInfoPageManageBean
     */
    public UserInfoManageBean() {
    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("UserInfoPageMangeBean: userId");
            userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            System.out.println("UserInfoPageMangeBean: userId" + userId);

//        listedUser = IUMA.ListUser();
//        System.out.println("UserInfoPageMangeBean: SIZE "+listedUser.size());
            UserEntity user = IUMA.getUser(userId);

            FirstName = user.getFirstName();
            MidName = user.getMidName();
            LastName = user.getLastName();
            Title = user.getTitle();
            Gender = user.getGender();
            Position = user.getPosition();
            Department = user.getDepartment();
            userLevel = user.getUserLevel();
            birthday = user.getBirthday();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            birString = format.format(birthday.getTime());
            System.out.println("UserInfoPageManageBean: birthday Calendar: " + birthday.getTime().toString());
            System.out.println("UserInfoPageManageBean: birthday: " + birString);
            Email = user.getEmail();
            Address = user.getAddress();
            Postal = user.getPostalCode();
            password = user.getPwd();
            departmentId = user.getDepartmentId();
        } catch (Exception e) {
            System.out.println("unexpected exception occured");
            e.printStackTrace();
        }

    }

    public InternalUserAccountManagementModuleLocal getIUMA() {
        return IUMA;
    }

    public void setIUMA(InternalUserAccountManagementModuleLocal IUMA) {
        this.IUMA = IUMA;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMidName() {
        return MidName;
    }

    public void setMidName(String MidName) {
        this.MidName = MidName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPostal() {
        return Postal;
    }

    public void setPostal(String Postal) {
        this.Postal = Postal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getBirString() {
        return birString;
    }

    public void setBirString(String birString) {
        this.birString = birString;
    }

    public String getInputOldPass() {
        return inputOldPass;
    }

    public void setInputOldPass(String inputOldPass) {
        this.inputOldPass = inputOldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public void saveChangedUserInfo(ActionEvent event) {
        try {
            System.out.println("UserInfoManageBean: save changes");
            System.out.println("UserInfoManageBean: birString to Date to Calendar:" + birthday.getTime().toString());

            IUMA.ModifyStaff(userId, Department, userLevel, LastName, MidName, FirstName,
                    Position, birthday, Gender, Title, Address, Postal, Email, departmentId);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "User Info Changed Successfully!", ""));
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void changePassword(ActionEvent event) {
        try {
            //System.out.println(FacesContext.getCurrentInstance().getAttributes().get("pwd"));
            System.out.println("UserInfoManageBean: change password");
            //System.out.println(FacesContext.getCurrentInstance().getMessages("messagesStatus"));
            System.out.println("UserInfoManageBean: old password" + password + " and input password " + inputOldPass + " and new pass " + newPass);
            if (cryptographicHelper.doMD5Hashing(inputOldPass + userId).equals(password)) {
                //System.out.println("\n\n\nIMPORTANT!!!: New password before hashing: "+ newPass +" Just for check!\n\n\n");
                IUMA.changePass(cryptographicHelper.doMD5Hashing(newPass + userId), userId);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Password changed successfully!", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Wrong Password, please enter again!", ""));
            }

            inputOldPass = null;
            password = IUMA.getUser(userId).getPwd();
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
}
