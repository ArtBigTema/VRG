package googlemapid.sample;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

public class GeodecodingSample extends AbstractSample {
    public static void main(final String[] args) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// ���� � Geocoding API �� HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("language", "ru");// ���� ������ �� ������� �� ����� ��������
        params.put("sensor", "false");// ������� �� ������ �� �������������� �� ���������� � �������� ��������������
        // ��������� �������� ������/�������, ��� �������� ������� �������� ��������� �������� �������� �����, ������ �
        // ������ ����������� �������, ����� �� ����������� �������
      //  params.put("latlng", "55.735893, 37.527420");
        params.put("latlng", "53.222627, 50.173065");
        final String url = baseUrl + '?' + encodeParams(params);// ���������� ���� � �����������
        System.out.println(url);// ����, ��� �� ����� ���� ���������� � �������� ����� ������
        final JSONObject response = JsonReader.read(url);// ������ ������ � ���������� � �������� �� ���� �����
        // ��� ������� �������� ���������� ����� ������ � ������ � ������ ����� �������� �� ����
        // //results[0]/formatted_address
        final JSONObject location = response.getJSONArray("results").getJSONObject(0);
        final String formattedAddress = location.getString("formatted_address");
        System.out.println(formattedAddress);// �������� �����
    }
}