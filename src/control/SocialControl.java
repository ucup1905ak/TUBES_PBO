/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.SocialDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.Social;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class SocialControl implements IGenericControl<Social, Integer>{
    private SocialDAO dao = new SocialDAO();
    @Override
    public int add(Social social) throws DatabaseException {
        return dao.add(social);
        
    }

    @Override
    public Social get(Integer id) throws DatabaseException {
        return dao.get(id);
    }

    @Override
    public List<Social> fetchAll() throws DatabaseException {
        return dao.fetchAll();
    }

    @Override
    public int update(Social social) throws DatabaseException {
        return dao.update(social);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        return dao.delete(id);
    }
    
    
    public List<Social> findByUserId(Integer user_id) throws DatabaseException {
        return dao.findByUserId(user_id);
    }
    
}
