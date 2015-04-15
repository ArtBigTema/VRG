package googlemapid.sample;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public class DistanceMatrixSample extends AbstractSample {
    public static void main(final String[] args) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/distancematrix/json";// ���� � Geocoding API �� HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// ���������, ������� �� ������ �� �������������� �� ���������� � ��������
        params.put("language", "ru");// ���� ������
        params.put("mode", "driving");// ���� ������, ����� ���� driving, walking, bicycling
        // ����� ��� ���������� ��������� �������
      
        final String[] origins = { //"������, ������, ����� ���������, 12",
        		"������, ������, ����� �������������, 52"  };
        params.put("origins", Joiner.on('|').join(origins));
        // ����� ��� ���������� ������� ����������
        final String[] destionations = { //
                // "������, ������, ������� ����� ���� ������", //
                //  "������, ������, ������� ����� �����������" //
               // "������, ������, ������� ����� ����������", //
               //  "������, ������, ������� ����� ����������" //
        		"������, ������, ����� ����� ������, 41",
        		"������, ������, ����� ���������, 190"
        };
        // � ������� ������ ������ ���������� �������� '|'
        params.put("destinations", Joiner.on('|').join(destionations));
        final String url = baseUrl + '?' + encodeParams(params);// ���������� ���� � �����������
        System.out.println(url); // ����� ��������� ��� ������ ���� ���� � ��������
        final JSONObject response = JsonReader.read(url);// ������ ������ � ���������� � �������� �� ���� �����
        final JSONObject location = response.getJSONArray("rows").getJSONObject(0);
        final JSONArray arrays = location.getJSONArray("elements");// ����� ����� ��� ����������� ��������
        // ���� ���� �� ������� �� �������� ������� �������
        final JSONObject result = Ordering.from(new Comparator<JSONObject>() {
            @Override
            public int compare(final JSONObject o1, final JSONObject o2) {
                final Integer duration1 = getDurationValue(o1);
                final Integer duration2 = getDurationValue(o2);
                return duration1.compareTo(duration2);// ���������� �� ������� � ����
            }
            private int getDurationValue(final JSONObject obj) {
                try {
                    return obj.getJSONObject("duration").getInt("value");
                } catch (final JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).min(new AbstractIterator<JSONObject>() {// � ��������� JSONArray ������ �����������, �� ����� ������� ���
            private int index = 0;

            @Override
            protected JSONObject computeNext() {
                try {
                    JSONObject result;
                    if (index < arrays.length()) {
                        final String destionation = destionations[index];
                        result = arrays.getJSONObject(index++);
                        result.put("address", destionation);// ������� ����� � ��������� � �����, ������ ��� ��� ��� �
                        // ���� ��������
                    } else {
                        result = endOfData();
                    }
                    return result;
                } catch (final JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        final String distance = result.getJSONObject("distance").getString("text");// ���������� � ����������
        final String duration = result.getJSONObject("duration").getString("text");// ����� � ����
        final String address = result.getString("address");// �����
        System.out.println(address + "\n" + distance + "\n" + duration);
    }
}