/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import java.util.Calendar;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface InternalUserAccountManagementModuleLocal {

    //public void AddStaff(String department, Integer userLevel, String lastName, String firstName, String position, String gender, String departmentID);

    public void DeleteStaff();

    public void ModifyStaff();

    public void AddStaff(String department, Integer userLevel, String lastName, String midName, String firstName, String position, Calendar birthday, String gender, String title, String address, String postalCode, String email, String departmentID);
    
}
