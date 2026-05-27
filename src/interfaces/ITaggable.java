/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.Tag;
import java.util.List;

/**
 *
 * @author farel
 */
public interface ITaggable {

    public List<Tag> getTags();

    public void setTags(List<Tag> tags);

    public void addTag(Tag tag);

    public void removeTag(Tag tag);
}
