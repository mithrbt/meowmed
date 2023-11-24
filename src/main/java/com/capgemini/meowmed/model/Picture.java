package com.capgemini.meowmed.model;
import jakarta.persistence.*;


@Entity
@Table(name = "bilder")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @Lob
    private byte[] imageData;

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
