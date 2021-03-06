/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface CustomerWebMemberModuleLocal {

    public MemberEntity getMember(String Email);

    public void AddMember(String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email);

    public void DeleteMember(Long userId);

    public void ModifyMember(Long userId, String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email);

    public List<MemberEntity> ListMember();

    public boolean AddMemberWithPassword(String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email, String password,String web);

    public MemberEntity memberLogin(String email, String pwd);
    
    public void upDateShoppingCart(Long userId,List<ShoppingCartItemEntity> itemList);
    
    public void removeItem(Long memberId,Long ShoppingCartItemId);
    
    public void createFeedBack(String title, String content, String email,String name);
    
    public void changePwd(Long memberId, String pwd);
    
    public String resetPass(String userId) ;
    
}
