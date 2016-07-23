package com.example.taojt.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taojt on 2016/7/23.
 */
public class NewsTitleFrag extends Fragment {
    private ListView newsTitleListView;
    private List<News> newsList = new ArrayList<News>();
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        adapter = new NewsAdapter(activity, R.layout.news_item,newsList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = newsList.get(i);
                if(isTwoPane){
                    Log.i("Haha","1111");
                    NewsContentFrag newsContentFrag = (NewsContentFrag) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFrag.refresh(news.getTitle(),news.getContent());

                }else {
                    NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                }
            }
        });
        return view;
}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane = true;
            Log.i("Haha","2222");
        }else {
            isTwoPane = true;
            Log.i("Haha","3333");
        }
    }

    public List<News> getNews() {
        newsList = new ArrayList<News>();

        News news1 = new News("习近平致信祝贺中国地质博物馆建馆100周年", "新华社北京7月23日电 日前，中共中央总书记、国家主席、中央军委主席习近平致信祝贺中国地质博物馆建馆100周年，并向全国广大地质工作者致以诚挚的问候" +
                "　　习近平在信中指出，100年来，中国地质博物馆恪守建馆宗旨、不断精进学术，在地球科学研究、地学知识传播等方面取得显著成绩，为发展我国地质事业、提高全民科学素质作出了重要贡献。");
        newsList.add(news1);
        News news2 = new News("土耳其政变，应该是一场连环阴谋！", "遭逮捕人数已达到9000人，含85名将军和上校，其中包括前空军司令阿金·厄兹蒂尔克、第二军军长、第三军军长、宪兵司令。另外，还包括土耳其总统埃尔多安的首席军事顾问阿里·亚泽哲。逾2700名法官和检察官被革职。　但如果将军们以为可以逃过一劫，显然也是过于乐观。在日前的支持者的集会上，对于支持者要求给予叛变者死刑的呼吁，土耳其总统埃尔多安这样说.");
        newsList.add(news2);

        News news3 = new News("菲律宾总统再发声:希望中国帮助菲发展经济", "菲律宾单方面发起的所谓“南海仲裁案”公布结果后，菲律宾表示出愿与中国加强合作、通过对话解决争端的意愿，据《菲律宾商报》7月23日报道，菲律宾总统杜特尔特22日称，希望中国帮助菲律宾经济发展。\n" +
                "\n" +
                "　　报道称，菲律宾单方面发起的所谓“南海仲裁案”公布结果后，杜特尔特在菲律宾马银兰佬省武努安社视察生物质发电厂期间说道：“如果能够与中国解决争端，我们将有许多益处。”");
        newsList.add(news3);
        return newsList;

    }
}
