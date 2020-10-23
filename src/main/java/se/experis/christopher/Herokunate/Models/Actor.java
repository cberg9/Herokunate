package se.experis.christopher.Herokunate.Models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")

    public class Actor{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
 
    @Column(nullable = false)
    public String firstName;
    
    @Column(nullable = false)
    public String lastName;
   
    @Column(nullable = false)
    public String dateOfBirth;
   
    @Column(nullable = false)
    public String urlto_imdb_page;

  



}