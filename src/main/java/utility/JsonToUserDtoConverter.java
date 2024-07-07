package utility;

import org.json.JSONObject;
import utility.DTO.User;

public class JsonToUserDtoConverter {
    public static User convertJsonToUser(JSONObject userObject) {
        User user = new User(userObject);

        // Extract and set user properties from JSON
        user.setId(userObject.getInt("id"));
        user.setName(userObject.getString("name"));
        user.setUsername(userObject.getString("username"));
        user.setEmail(userObject.getString("email"));

        // Extract and set address details
        User.Address address = new User.Address();
        JSONObject addressObject = userObject.getJSONObject("address");
        address.setStreet(addressObject.getString("street"));
        address.setSuite(addressObject.getString("suite"));
        address.setCity(addressObject.getString("city"));
        address.setZipcode(addressObject.getString("zipcode"));

        // Extract and set geo details
        User.Address.Geo geo = new User.Address.Geo();
        JSONObject geoObject = addressObject.getJSONObject("geo");
        geo.setLat(geoObject.getString("lat"));
        geo.setLng(geoObject.getString("lng"));

        address.setGeo(geo);
        user.setAddress(address);

        // Extract and set other details (phone, website, company)
        user.setPhone(userObject.getString("phone"));
        user.setWebsite(userObject.getString("website"));

        User.Company company = new User.Company();
        JSONObject companyObject = userObject.getJSONObject("company");
        company.setName(companyObject.getString("name"));
        company.setCatchPhrase(companyObject.getString("catchPhrase"));
        company.setBs(companyObject.getString("bs"));

        user.setCompany(company);

        return user;
    }
}
