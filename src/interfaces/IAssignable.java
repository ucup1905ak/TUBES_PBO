/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.User;
import java.util.List;

/**
 *
 * @author farel
 */
public interface IAssignable {

    List<User> getAssignee();

    void addAssignee(User assignee);

    void removeAssignee(User assignee);
}
