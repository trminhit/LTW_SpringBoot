package ltwebct4.model;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Integer categoryId;
    
    @NotEmpty
    @Length(min = 5)
    private String categoryName;
    
    private String categoryCode; 
    
    private String images; 
    
    private int status; 
    
    private Boolean isEdit = false;
}