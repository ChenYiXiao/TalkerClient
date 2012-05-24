package protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * @author Administrator
 *
 */

public class HttpUtil {
	private static HttpClient httpClient;
	
	/**
	 * @param url ���������Url
	 * @return ��������Ӧ�ַ���
	 * @throws Exception 
	 */
	public static String getRequest(String url) 
			throws Exception
	{
		HttpGet get=new HttpGet();
		HttpResponse httpResponse=httpClient.execute(get);
		if(httpResponse.getStatusLine().getStatusCode()==200)
		{
			String result=EntityUtils.toString(httpResponse.getEntity());
			return result;
		}

		return null;
	}
	
	/**
	 * @param url ���������Url
	 * @param rawParams �������
	 * @return ��������Ӧ���ַ���
	 * @throws Exception
	 */
	public static String postRequest(String url,Map<String, String> rawParams) throws Exception
	{
		HttpPost post=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		for(String key:rawParams.keySet())
		{
			params.add(new BasicNameValuePair(key, rawParams.get(key)));
		}
		post.setEntity(new UrlEncodedFormEntity(params,"gbk"));
		HttpResponse httpResponse=httpClient.execute(post);
		if(httpResponse.getStatusLine().getStatusCode()==200)
		{
			String result=EntityUtils.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}
}
