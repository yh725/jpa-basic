package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "AlbumJPA")
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
}
