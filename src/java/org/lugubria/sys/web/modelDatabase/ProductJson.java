package org.lugubria.sys.web.modelDatabase;

/**
 *
 * @author angel
 */
public class ProductJson {
    
    private String name;
    private String codOrigin;
    private String categoryName;
    private String factoryName;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodOrigin() {
        return codOrigin;
    }

    public void setCodOrigin(String codOrigin) {
        this.codOrigin = codOrigin;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
