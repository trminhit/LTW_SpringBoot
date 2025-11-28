package ltwebct4.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class Video implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "VideoId")
	private String videoId;
	
	@Column(name="Title", columnDefinition = "NVARCHAR(255)")
	private String  title;
	
	@Column(name="Poster", columnDefinition = "NVARCHAR(500)")
	private String  poster;
	
	@Column(name="Description", columnDefinition = "LONGTEXT")
	private String  description;
	
	@Column(name="Views")
	private int views;
	
	@Column(name="Active")
	private int active;
	
	@ManyToOne
    @JoinColumn(name = "CategoryId")
	private Category category;



}
