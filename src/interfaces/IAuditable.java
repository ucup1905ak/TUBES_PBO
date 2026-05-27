/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.Timestamp;

/**
 *
 * @author farel
 */
public interface IAuditable {

    public Timestamp getCreatedAt();

    public void setCreatedAt(Timestamp createdAt);

    public Timestamp getUpdatedAt();

    public void setUpdatedAt(Timestamp updatedAt);
}
