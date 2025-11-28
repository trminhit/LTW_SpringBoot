package ltwebct4.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private Integer categoryId;
	
	@Column(name = "CategoryName", columnDefinition = "NVARCHAR(255)")
	private String categoryName;
	
	@Column(columnDefinition = "NVARCHAR(500)")
	private String images;
	
	@Column(name = "CategoryCode", columnDefinition = "VARCHAR(50)")
	private String categoryCode;
	
	@Column(name="Satus")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Video> videos;
}
