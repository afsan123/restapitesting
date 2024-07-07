package utility.DTO;

import org.json.JSONObject;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(JSONObject jsonObject) {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.username = jsonObject.getString("username");
        this.email = jsonObject.getString("email");

        // Populate other fields here...
        this.phone = jsonObject.getString("phone");
        this.website = jsonObject.getString("website");

        // Address parsing
        JSONObject addressObject = jsonObject.getJSONObject("address");
        address = new Address();
        address.setStreet(addressObject.getString("street"));
        address.setSuite(addressObject.getString("suite"));
        address.setCity(addressObject.getString("city"));
        address.setZipcode(addressObject.getString("zipcode"));

        // Geo parsing within Address
        JSONObject geoObject = addressObject.getJSONObject("geo");
        address.setGeo(new Address.Geo());
        address.getGeo().setLat(geoObject.getString("lat"));
        address.getGeo().setLng(geoObject.getString("lng"));

        // Company parsing
        JSONObject companyObject = jsonObject.getJSONObject("company");
        company = new Company();
        company.setName(companyObject.getString("name"));
        company.setCatchPhrase(companyObject.getString("catchPhrase"));
        company.setBs(companyObject.getString("bs"));
    }


    // Getters and setters...


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        // Getters and setters...


        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        public static class Geo {
            private String lat;
            private String lng;

            // Getters and setters...

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }
        }
    }

    public static class Company {
        private String company_name;
        private String catchPhrase;
        private String bs;

        // Getters and setters...

        public String getName() {
            return company_name;
        }

        public void setName(String name) {
            this.company_name = name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        public void setBs(String bs) {
            this.bs = bs;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getName(), user.getName()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getWebsite(), user.getWebsite()) && Objects.equals(getCompany(), user.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsername(), getEmail(), getAddress(), getPhone(), getWebsite(), getCompany());
    }
}
