/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.Session;

/**
 *
 * @author farel
 */
public interface IAuthService {

    public Session authenticate(String username, String passwordHash);

    public Session getCurrentSession(String token);
}


