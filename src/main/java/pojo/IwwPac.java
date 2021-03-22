package pojo;

import java.util.ArrayList;

public class IwwPac {

    String _createdDate;
    String _modifiedDate;
    String _schemaVersion;
    String type;
    String version;
    String title;
    boolean _isSellable;

    Person person;

    Object identifier;

    Object availability[] ;

    ArrayList<Source> sources = new ArrayList<Source>();

    public String get_createdDate() {
        return _createdDate;
    }

    public void set_createdDate(String _createdDate) {
        this._createdDate = _createdDate;
    }

    public String get_modifiedDate() {
        return _modifiedDate;
    }

    public void set_modifiedDate(String _modifiedDate) {
        this._modifiedDate = _modifiedDate;
    }

    public String get_schemaVersion() {
        return _schemaVersion;
    }

    public void set_schemaVersion(String _schemaVersion) {
        this._schemaVersion = _schemaVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean get_isSellable() {
        return _isSellable;
    }

    public void set_isSellable(boolean _isSellable) {
        this._isSellable = _isSellable;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Object getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Object identifier) {
        this.identifier = identifier;
    }

    public Object[] getAvailability() {
        return availability;
    }

    public void setAvailability(Object[] availability) {
        this.availability = availability;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(Source source) {
        this.sources.add(source);
    }
}