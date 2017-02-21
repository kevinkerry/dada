package com.youyisi.vote.application.weixin.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.vote.application.user.UserService;
import com.youyisi.vote.application.weixin.WeiXinService;
import com.youyisi.vote.domain.image.Image;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.domain.weixin.Article;
import com.youyisi.vote.domain.weixin.NewsMessage;
import com.youyisi.vote.domain.weixin.TextMessage;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
import com.youyisi.vote.infrastructure.util.MessageUtil;

/**
 * 
 * @author shuye
 *
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {
	private String url = "http://vote.dadasports.cn";
	private String image = "http://image.dadasports.cn";

	@Autowired
	private UserService userService;

	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;

		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			String picUrl = requestMap.get("PicUrl");
			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义

			String content = requestMap.get("Content");
			System.out.println("----------------------" + content);
			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if ("1010".equals(content)) {
					respMessage = sendLuckMessage(fromUserName, newsMessage,
							articleList);
				}
				if ("171010".equals(content)) {
					respMessage = sendLuckResultMessage(fromUserName, newsMessage,
							articleList);
				}
				if ("电影票".equals(content)) {
					respMessage = sendFilmActivity(fromUserName, newsMessage,
							articleList);
				}
				if (content.contains("0#")) {
					String id = content.replace("0#", "");
					User u = userService.get(Long.parseLong(id));
					if (u != null) {
						Image headImage = u.getImages().get(0);
						String head = headImage.getImageUrl();
						String[] images = head.split("\\.");
						String[] imagename = images[0].split("\\!");
						Article article = new Article();
						article.setTitle("哒哒运动——欢迎参加哒哒运动运动哒人评选，我是"
								+ u.getName() + ",请投我一票，谢谢！");
						article.setDescription("欢迎参加哒哒运动运动哒人评选，我是"
								+ u.getName() + ",请投我一票，谢谢！");
						article.setPicUrl(image + imagename[0] + "!500x500."
								+ images[1]);
						article.setUrl(url + "/user/voteuser?id=" + u.getId()
								+ "&fromUserName=" + fromUserName);
						articleList.add(article);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}

				}

				if ("1".equals(content)) {

					Article article = new Article();
					article.setTitle("别人都在“炫腹”了，你还在炫水桶腰？");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvWwsmcAfCiaawtTZMf1IEaZ19spWVxickgtvQl9aEGKJr3qiaFdE4SsfKvQ/0?wx_fmt=jpeg");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400893613&idx=1&sn=db1e3f43da3e956d3dad92ef37715b84#rd");
					articleList.add(article);
					Article article2 = new Article();
					article2.setTitle("小蛮腰修炼大法，快收了！");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvW4D7CIl8MXFOG9UD3QONCibibzwU6FgBJuMzQFQRmuuFuhkBBRSFwCjWA/0?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400893613&idx=2&sn=04190a870dfadc74bc9d410ea70d7093#rd");
					articleList.add(article2);
					Article article3 = new Article();
					article3.setTitle("爱恨就在一“线”间");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9A2zCW37PvPMuicLN3PhIGhZvzbWiav8EjsmicLpia2qfxicTH0Pg2xVGAcDPDuG85qIlno8oia4JZa8TbQ/640?wxfmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400893613&idx=3&sn=72b81ffbf2feead7b4c22b84586675f1#rd");
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if ("2".equals(content)) {

					Article article = new Article();
					article.setTitle("屁这么点事，就能让你sexy一辈子！");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DcsxLgSgc2ibspqOzlCV1ytoUJH1H3yLHdk4HwaItkvODWYHVP1kgVk1F9W34dRumb9vXT2BicEXgg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400900942&idx=1&sn=a1177a827018323ed27517adff1b6d27#rd");
					articleList.add(article);

					Article article2 = new Article();
					article2.setTitle("公狗腰，翘臀，性感部位等你备齐！");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DW3BiafXyzvpBETrdJloZX7e9t7f7GfCzPpndJbxDswFfejficyfSBeIFCv1VExe5zSZI1rOdNputg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400900942&idx=2&sn=a7e174dcbb6adeaadc719380417e71f4#rd");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("古有靓靓拳，今有翘翘法！");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvWxfZS6hAjMt0Kqf97kHAdIErIeF60mLsEgeLVtyibVLkoAqA33EbOmvA/0?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400900942&idx=3&sn=2aa3cc63f02b4c59c51fed25e9bad943#rd");
					articleList.add(article3);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);

				}
				if ("3".equals(content)) {
					Article article = new Article();
					article.setTitle("跑后拉伸真的能瘦小腿？不一定！要这样拉才能瘦......");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvWJ0qTmlsSVUVvBujNPbTSshEZX0q9djOV3byVZ0VYjC3Z6gn5YBtXcg/0?wx_fmt=jpeg");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400898916&idx=1&sn=7697093a90479982555b17fbfbffcf71#rd");
					articleList.add(article);

					Article article2 = new Article();
					article2.setTitle("瘦腿动作，再不收了有难找啦！");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DLEXCqNjVprYbFALRicl5Qibeot9yYrQ9gpTFhhUJ09qCamWQgBeheqm2wDDaAcaScR9Kj4rfPibKnw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400898916&idx=2&sn=97f6bfbc1ad8fb1d46370331771254be#rd");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("还羡慕别人的美腿？快把美腿大法收了吧！");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvWKwhdT88fuKbK5dFqSNqsBDu2UBJSlaubdpAS8jm0a3RORlVXuNt9yw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400898916&idx=3&sn=17175115c57bbd188a0a0a80d53e4aa1#rd");
					articleList.add(article3);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if ("4".equals(content)) {
					Article article = new Article();
					article.setTitle("离别挥手说再见，胳膊底下肉狂甩。");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClF6icHFnQZqeicNornIyGpl1icCRGmjcgcwCUAauuKjBxIJfB1zbelibweM8tO47ASbo1yFYFOUsHrQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400895589&idx=1&sn=f5bbfde72446904f0b81b1e4cb7be502#rd");
					articleList.add(article);

					Article article2 = new Article();
					article2.setTitle("纤细玉臂，谁不想要");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9Bev4FmPOuUypXgJxRWwYvWoicvm6WCdeVmm4WECOumVe2kB3d5k3XQDanGGDCg0x52m7Yxfn3pOKA/0?wx_fmt=gif&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400895589&idx=2&sn=8e126c5d1dc0762d6880b0392634ae96#rd");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("招让你甩掉蝴蝶袖，Get√！");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClF6icHFnQZqeicNornIyGpl8ibTzsq8ichG8iaVB7SKX1qmTGAQk3fKzhVASveOmYPx8FOFT3S1vJr9w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400895589&idx=3&sn=9b952ac70483a8da8ef4a00d6a67a96b#rd");
					articleList.add(article3);


					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if ("5".equals(content)) {
					Article article = new Article();
					article.setTitle("“力拔山兮气盖世”，想要妹子就要先练肱二头肌！");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/rJ55CP1FKkRddjfe3qCKVXm3lTDp2fibvRrjF95TY95ywSR2Cnw5ImicG9YV5aAqNoJGc3QRDufSVTXYP0ZtnZaw/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1&tp=webp");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400907674&idx=1&sn=4ab47ad15fab51c902d07c29b56e718b#rd");
					articleList.add(article);

					Article article2 = new Article();
					article2.setTitle("男人健身不健腿，迟早变阳痿！");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9Ah4IGCia9mNtoZxrKRtxdnA6MmOnZZr6t7B3tCgib85ibXynuBrVQta1tIFV5fcMTXFPD7MtA3oPgjA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400907674&idx=2&sn=09c6ee9a55904c0c84b48b92ab406403#rd");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("谁说只能女人有大胸，男人也可以！");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClF6icHFnQZqeicNornIyGpldLZr4Z8Ck7jAibzByzOMkza4xvhnicmJRxCM6MZRiaF5kmiaQvRjrX4Wuw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400907674&idx=3&sn=5bb1d6622f2ea68e8b39065ad3aa2bc1#rd");
					articleList.add(article3);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if ("6".equals(content)) {
					Article article = new Article();
					article.setTitle("无氧间歇锻炼计划，增肌减脂事半功倍！");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/KlBA3F7cdBkozWSic3hdYKR7oibDG9G5ia9YLxttylU6ynVApzXpCDex09lOiaocDGicfCvGG1d2OuVMVpicqcdTwfcw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=1&sn=e6703a7b5cd84468f0a5d81cbd236482#rd");
					articleList.add(article);

					Article article2 = new Article();
					article2.setTitle("90%的人跑步都白跑了，不但伤膝盖，还会导致瘫痪！因为.......");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/zpKUbk6ic8Xib9RHEfbtGsBYoU0s2emNZRvK2frTy7WRyOzsX9J7736CbF5jXQpicDa6nPpD8lsIM08OibYsIwAZLw/640?wx_fmt=jpeg&wx_lazy=1&wxfrom=5&tp=webp");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=2&sn=fad63d55383909f9dfbc60bd7805e74a#rd");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("为什么你跑了半个月的步但并没有什么卵用？（只有腿粗了..）");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/IYRGiaonLP52STDib50I8yprtibzicA6BkpHXlrH58SN2z4cVuW475p42RPLhAeoRib7bTVjcK41jcEOhyEvM03jASw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&tp=webp");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=3&sn=4008a3e9d1d8116908ef1ad0572f8033#rd");
					articleList.add(article3);

					Article article4 = new Article();
					article4.setTitle("各部位30天训练法大全集");
					article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/icWPRldcV2TW8YZRFc7q9W5liaEAqInvNpu0B1CcxEpxK9I3xK95ibkrBcHdxeMTJRZ0UctT0ttibAkic1RKlU6XLBA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=4&sn=b564e950c14987f220f6b52ef0dd8e80#rd");
					articleList.add(article4);


					Article article5 = new Article();
					article5.setTitle("九大健身误区，不看等于白练！");
					article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9BbmfAASiaTXhC3FdlvKYIRsqE323ph0yFiaibYc1NmmvaQicQjtv5tdeQ8V7bP632FjKMfOQSMz0X7VQ/640?wxfmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=5&sn=7ac8dd0a9b735b077541c5a8c819896b#rd");
					articleList.add(article5);


					Article article6 = new Article();
					article6.setTitle("膝盖损伤与恢复。");
					article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9D3RTuUX72Q8FJbr4SlictBwL5gTGQafFYiapz8icLU4uvC673iaibpXazRVia813nB2epvziaW7MZApMWMA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=400928612&idx=6&sn=8123473abd1be057854b7af13789aa49#rd");
					articleList.add(article6);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if ("精彩回放".equals(content)) {

					Article article2 = new Article();
					article2.setTitle("【你丑你先睡，我美我运动】校园荧光跑—华师站！");
					article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C1ib1p1S2IQWPzVJ8gReGjynBFgXbB87m2jibx0bE2oE8XjAJOtIUB7GgPFov1VBcPwVybOic3NUZ2g/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=229717072&idx=2&sn=8dd35c6facd1d329cf19b2151f32a9b0&scene=1&srcid=1019WXgAsxF7Jo9es6Y1T2Bd&key=b410d3164f5f798ee115a0cffc6a1d62b4f16c61a8d79c82aae44b2da13729ce87306e11677c85a86a270d639ed4aba8&ascene=1&u");
					articleList.add(article2);

					Article article3 = new Article();
					article3.setTitle("聚龙钓鱼基地首届“思泊杯”大赛圆满成功——精彩花絮。");
					article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C8G4zMpJgluhUBKvc22rOPDqjfsAWytGqSc30tf6zyAFjvqjmo19RLPTwQXgdIicPfF8AOApZP8Tg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=225837512&idx=2&sn=c476da5d82d775866bc896d81bb6fdbc&scene=1&srcid=10196cxRm8wKDd9MiCfVYPxz&key=b410d3164f5f798e3e9687e025bbc9ea47f101838be784da4f9740b905ef35b5690f33c9d62ac2349145413137b62a05&ascene=1&u");
					articleList.add(article3);

					Article article4 = new Article();
					article4.setTitle("这是全广州颜值最高的运动家族，没有之一！");
					article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DNAQicNafVq28HGVnZklk2QRiaLxBBDXXe7UEQGChT2C6rGHr1p1DibdYURdl6vaB8Wcohn3ddsMXDQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=224423567&idx=2&sn=c0e442c3052a7edc4887ac47ff4e322a&scene=1&srcid=1019bjZWOUqYB7WzFB1vomsB&key=b410d3164f5f798e76714a8c44b30c737a3cba17ffc179d4864504f890670177e951f28ceaa33a84b9ca75d9dc2cdfa5&ascene=1&u");
					articleList.add(article4);


					Article article5 = new Article();
					article5.setTitle("【互联英雄联盟】与其七夕相遇，不如心星相印");
					article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9BoleXFY9KofibxhMGf6wD3lh1dChvnUgDs3ptJ5d1xn2cRACOf4X2WGAFXqJHzkk7or46NhJIdEDw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=216352159&idx=1&sn=29308d0e42cd0513f5589156cbe79c38&scene=1&srcid=1019kfyV6Pqn19RBjEkFIoYg&key=b410d3164f5f798eb7545c9023144d702b65106204d25b33efae2f63af79c5cdec510dabe4129cc60c152095975ae6d6&ascene=1&u");
					articleList.add(article5);

					Article article6 = new Article();
					article6.setTitle("2015定向广州越野运动赛，嗨爆全城！");
					article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9D2kDmhZa5p2nNic5HsHWf8lia3RUh9ol3uVAhaxqD96TcbUuR9fOaXXZ0Fz0LuKsk065eFoUDNACtQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=213053315&idx=1&sn=ee338cb8d595e59c31081e3c6cb82fbe&scene=1&srcid=1019Avg3pItO9ZPQRz70Erc3&key=b410d3164f5f798ed6e0873aabad40aa6b3e0ae0a8397011dee21962683b98d5e5ced8dfaf72cf5b3a686c3822c469db&ascene=1&u");
					articleList.add(article6);

					Article article7 = new Article();
					article7.setTitle("【互联英雄联盟】义结金兰之逗比事儿");
					article7.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C3hw1wE0APxVNRJy9FPrRSUtqf5vXj3PMocqVD80tt2DAp5ZfmmOPZ7P0KibkL3kbnRzjLUbhXxCw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article7.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=212282123&idx=2&sn=857263d30978f74268aad1d6f371580e&scene=1&srcid=1019sCaJM4zZhgzFO9ctZ7O5&from=singlemessage&isappinstalled=0&key=b410d3164f5f798e746f3458f47b4187266ec553f202842627815fffb44786a187e2ef4");
					articleList.add(article7);

					Article article8 = new Article();
					article8.setTitle("哒哒运动：我们不请你吃饭，我们就请你流汗！");
					article8.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DCJO5pxVR1JuxzWUp2WHtHvibZY83gyyrw4InZZMfJzVfemx5q9OQEL0J3QH3UicrxMuDakDsXDNvw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article8.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=211136661&idx=1&sn=84a7e4a981e7a3d0db96a607e455031f&scene=1&srcid=1019lBRZuRmDR3E8AyGMahph&key=b410d3164f5f798e8ef7e4d30367ee47414be6c9241eb1a88a736e437ea8446d13f5f55cf5de2f78ce12ab8ec58bbcad&ascene=1&u");
					articleList.add(article8);

					Article article9 = new Article();
					article9.setTitle("校园哒人封神榜——俊男靓女都在我碗里！");
					article9.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9BZlYNGod6uSoaaLdtX2CJfrUzjXtSnOH9x8GZ9QB8S85KWk7QSH6IOjdpdLiajyDoXgXbbiaWmicxSg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article9.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=210919895&idx=1&sn=d1938b15fd95ed2b7070301d97dc1745#rd");
					articleList.add(article9);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			//RedisClient.set("vote:vote:imageurl:" + fromUserName,picUrl);
			//respMessage = sendLuckMessage(fromUserName, newsMessage,articleList);
				textMessage.setContent("小哒哒收到啦~ 爱你哦！我会尽快处理内容然后给您一个满意的答复。如果比较非常紧急的话，欢迎添加官方客服号：gzdadagj   么么哒！");
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");

				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

					StringBuffer replyContent = new StringBuffer();
					replyContent.append("欢迎加入哒哒运动大本营！\n");
					replyContent.append("让运动创造财富，坚持运动不再难！\n");
					replyContent.append("每周我们将会为你送上：\n");
					replyContent.append("最Hot的运动话题，最New的理财咨询！\n");
					replyContent.append("不定期也会举办好玩又有福利的活动！\n");
					replyContent.append("小伙伴请好好爱我，经常看看我哦~\n");
					textMessage.setContent(replyContent.toString());

					respMessage = MessageUtil.textMessageToXml(textMessage);
					// respMessage = sendVoteMessage(fromUserName, newsMessage,
					// articleList);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					System.out.println("取消关注：" + fromUserName);
					String openId = (String) RedisClient
							.get("vote:vote:fromuser" + fromUserName);
					if (StringUtils.isNotBlank(openId)) {
						openId = openId.substring(1, openId.length() - 1);

						System.out
								.println("key:" + "vote:vote:openid" + openId);

						RedisClient.delete("vote:vote:openid" + openId);
						RedisClient.delete("vote:vote:fromuser" + fromUserName);
					}
					
				
					
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("btn22")) {
						respMessage = sendVoteMessage(fromUserName,
								newsMessage, articleList);
					}
					
					if("newActivity".equals(eventKey)) {
						Article article2 = new Article();
						article2.setTitle("Hey！邀请好友注册就送体验金啦！");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/wD8bnia5Pz9CsB938icuErztHjicHYdBRxWKNqHxPhOF5ec9vZ18PqvjueP757v7Oe00qHkIQ9lmfhgk1kELumVibA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231247&idx=1&sn=4613a03080e74915c5a421c5441497b0&scene=1&srcid=0901ZTFgnhHNzL6fwrqVzG8H#wechat_redirect");
						articleList.add(article2);

						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					if (eventKey.equals("btnActivity")) {
						Article article2 = new Article();
						article2.setTitle("今早有一波荧光绿的生物出现在天河体育中心... ...");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpkCFmyBefvj31uoaTBW2mQPsbGjxSysq3Teibu1BjV5WEsXyjibAOY2AA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=1&sn=53427859f7132d372b7f6fb5501e893d&scene=1&srcid=0826FnQmONeKyVPKRkYma8IY#wechat_redirect");

					
						Article article3 = new Article();
						article3.setTitle("那一夜，他们竟然偷偷去“鬼混”！");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpoZibvuxgUqUDic4PaC3LYcruHnjbt9cHFoTJvscrV7AzHYp5o3iaeIGjA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=2&sn=85033c3f8868b89b7f3492d5c96f614a&scene=1&srcid=0826yWN60ALHqxtnxr8CGyAY#wechat_redirect");

						Article article4 = new Article();
						article4.setTitle("2015定向广州越野运动赛，嗨爆全城！");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpJIY26FXxx24H2G9zdmicvjvp8KKu8UNH6libN1pJqfDviaKqaHtIYmJtg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=3&sn=bdc9af7c43e966c96aa4803256312148&scene=1&srcid=0826h7XTFSSeMweRcZJaQ4nR#wechat_redirect");
						
						Article article5 = new Article();
						article5.setTitle("【互联英雄联盟】与其七夕相遇，不如心星相印");
						article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpD5FKquscahjWN91r2LFQETtKI0ZMykkhcLVYX3yYErPPvROlCeXd7Q/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=4&sn=23923a65891c32d745782c7fd23dc6aa&scene=1&srcid=0826HePvfGjmvQNYrHK19KHV#wechat_redirect");

						Article article6 = new Article();
						article6.setTitle("【互联英雄联盟】火炉山侠客行");
						article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpmQnko8PFgic1Q2VQTsEO5Rn4ABSOkG7Viaw3sIU84cfpDcujbRUe3tXQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=5&sn=69ffa1892e8b7fb42e0362a3dabf8010&scene=1&srcid=0826ZVHhaZxYUzx73tQ0CsSX#wechat_redirect");

						Article article7 = new Article();
						article7.setTitle("2015哒哒运动携手咏春进百企精彩大回顾");
						article7.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/PVmjpE6wIfMsrNicTicvic5iaolWVkq551jpRuTgicf4kOGYEmDKaexYxdzydngJkHG2DE0pEUDSehsMOxBSxicpzU5w/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article7.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=2658231186&idx=6&sn=91addb76fab56ac22565b1cd8b2c006f&scene=1&srcid=0826Ug9nFLHxgeZRPMlACIob#wechat_redirect");


						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						articleList.add(article6);
						articleList.add(article7);
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换成xml字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					
					if (eventKey.equals("btn23")) {
						
						
						Article article = new Article();
						article.setTitle("【哒哒运动】携咏春进百家企业】——上周精彩回顾");
						article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5F7DLZSYicDCQ322sjBIA6r1FvLUptP2EfugyHFltafQyvBOnXehCjfg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=1&sn=26f63d947fd6ce8469b894950a3f6633#rd");

						Article article1 = new Article();
						article1.setTitle("【哒哒运动】携咏春进百家企业】——上周精彩回顾");
						article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5Ya5OLLpeXwfOXWyAUdRzES0X9yMicxLYIMF0CBMnXPB3jibGykN2Jic5A/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=2&sn=cfcc1cb6071566ee20f49f4612c4782f#rd");
						


						Article article2 = new Article();
						article2.setTitle("【哒哒运动】携咏春进百家企业】——上周精彩回顾");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5ZGLXC70UV24ePicuoXzjstnficEJk0ia30je5m5G7vk0FzPZPbuU7Iqqw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=3&sn=df2b515e8344f0d5d8e6587559ad5030#rd");

						Article article3 = new Article();
						article3.setTitle("没有资源交互的线下活动不是好社交，快来跟哒哒一起练咏春吧");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5ryHbAX0ibkcx4iaxJvFsUYHicRqq97JTPWOrmbmClo4FaITFYfMSYTeWQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=4&sn=33466257e234a6ace4e15db4651e7245#rd");

						Article article4 = new Article();
						article4.setTitle("红色军团，重拳出击——哒哒运动携手龙保咏春走进国美电器");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5tiaetnE1ibzwG9dalHoMsuCBeICag5ueMpyn9rsXKPPnbnStXPDUl4Uw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=5&sn=5fc50736cde137dc4363c10371c20516#rd");

						Article article5 = new Article();
						article5.setTitle("灯火辉煌的街头，突然袭来了一阵拳！风！");
						article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5dH4gMpxRYloU2lJ5PE9ZfdrY9DHfnibpiaGvNxSibmTWm1Vw1DV7iawYAA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=6&sn=3d7ec8eb3c46fb1a5d73b0818478d24b#rd");

						Article article6 = new Article();
						article6.setTitle("报告老板！咏春进百家系列活动来励丰了！");
						article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5tKESmDARtF7gQYxueGbvZW0MWb4PiauDmSEY7nAoUy2vic19lDtvrNdA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=7&sn=8cb96e22471a77ca6fd2cbd8e54af744#rd");

						Article article7 = new Article();
						article7.setTitle("哒哒运动携手龙保咏春走进百家企业——走进加和");
						article7.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9CscYYaG81AywozsD03Npib5s9B9glNEbJbm6hCdib0D2YibQx9uiaW7aKrWKicUwwENibtf98cjf4CboMA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article7.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=510746771&idx=8&sn=421478763414d4eb37a529635a3c3dc1#rd");

						articleList.add(article);
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						articleList.add(article6);
						articleList.add(article7);
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换成xml字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					if("videoStation".equals(eventKey)) {
						Article article = new Article();
						article.setTitle("【动态】哒哒运动有声电台开播啦~");
						article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClP9VLFReZTEKwdFnwZaJSQkEntyV2gNpFeHaacRuYx7xLLdjENTkBqnxT2aGTYX8WanPIWDMXBA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=402454020&idx=1&sn=f93fc2af09b09da1010c4c81d5d3e3d0");
						articleList.add(article);

						Article article2 = new Article();
						article2.setTitle("【互联网】互联网er们，是时候起来动啦！");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClP9VLFReZTEKwdFnwZaJSE6EAibGSeVmcyQDjfia0p9NaaakypA3U2lbibF7RhmWcpf8yZuScWlSXA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=402454020&idx=2&sn=8d34612be94047e6549ca75ceef1c631");
						articleList.add(article2);

						Article article3 = new Article();
						article3.setTitle("【活动】嗨~我有个上千人的活动跟你聊聊");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClP9VLFReZTEKwdFnwZaJSaf7AibkO1ibmYkxnwskfXDcTUOzV3DWvPBXleEI6HmRfsz70DXT7Mlqw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=402454020&idx=3&sn=ba777ca3e9721585c8b5794a4910cab4");
						articleList.add(article3);

						Article article4 = new Article();
						article4.setTitle("【美文】奔跑，奔跑，在黑夜迈开沉重的脚");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9ClP9VLFReZTEKwdFnwZaJSdaZ6slWBHfejrEb2V8gjUDCZaJfPetJjlUibZugYx9z7exG1Lt8Tocw/640?wx_fmt=jpeg&tp=webp&wxfrom=5");
						article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=402454020&idx=4&sn=a6aa9b5e338d6ab952c24349782e08a8");
						articleList.add(article4);

						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}

					if("lastActivity".equals(eventKey)) {

						Article article2 = new Article();
						article2.setTitle("万圣节晚会报名");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DXwRWX38lYLlwU5TO0SxYVH5RmMN7WRbsY5PI3LFAVNpGradldTKUibR0NLBdzagDIUlFM8W1gACg/640?tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://u.eqxiu.com/s/bscqDAjh?eqrcode=1");
						articleList.add(article2);

						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					
					


					if ("wonderful".equals(eventKey)) {

						Article article2 = new Article();
						article2.setTitle("【你丑你先睡，我美我运动】校园荧光跑—华师站！");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C1ib1p1S2IQWPzVJ8gReGjynBFgXbB87m2jibx0bE2oE8XjAJOtIUB7GgPFov1VBcPwVybOic3NUZ2g/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=229717072&idx=2&sn=8dd35c6facd1d329cf19b2151f32a9b0&scene=1&srcid=1019WXgAsxF7Jo9es6Y1T2Bd&key=b410d3164f5f798ee115a0cffc6a1d62b4f16c61a8d79c82aae44b2da13729ce87306e11677c85a86a270d639ed4aba8&ascene=1&u");
						articleList.add(article2);

						Article article3 = new Article();
						article3.setTitle("聚龙钓鱼基地首届“思泊杯”大赛圆满成功——精彩花絮。");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C8G4zMpJgluhUBKvc22rOPDqjfsAWytGqSc30tf6zyAFjvqjmo19RLPTwQXgdIicPfF8AOApZP8Tg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=225837512&idx=2&sn=c476da5d82d775866bc896d81bb6fdbc&scene=1&srcid=10196cxRm8wKDd9MiCfVYPxz&key=b410d3164f5f798e3e9687e025bbc9ea47f101838be784da4f9740b905ef35b5690f33c9d62ac2349145413137b62a05&ascene=1&u");
						articleList.add(article3);

						Article article4 = new Article();
						article4.setTitle("这是全广州颜值最高的运动家族，没有之一！");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DNAQicNafVq28HGVnZklk2QRiaLxBBDXXe7UEQGChT2C6rGHr1p1DibdYURdl6vaB8Wcohn3ddsMXDQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=224423567&idx=2&sn=c0e442c3052a7edc4887ac47ff4e322a&scene=1&srcid=1019bjZWOUqYB7WzFB1vomsB&key=b410d3164f5f798e76714a8c44b30c737a3cba17ffc179d4864504f890670177e951f28ceaa33a84b9ca75d9dc2cdfa5&ascene=1&u");
						articleList.add(article4);


						Article article5 = new Article();
						article5.setTitle("【互联英雄联盟】与其七夕相遇，不如心星相印");
						article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9BoleXFY9KofibxhMGf6wD3lh1dChvnUgDs3ptJ5d1xn2cRACOf4X2WGAFXqJHzkk7or46NhJIdEDw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=216352159&idx=1&sn=29308d0e42cd0513f5589156cbe79c38&scene=1&srcid=1019kfyV6Pqn19RBjEkFIoYg&key=b410d3164f5f798eb7545c9023144d702b65106204d25b33efae2f63af79c5cdec510dabe4129cc60c152095975ae6d6&ascene=1&u");
						articleList.add(article5);

						Article article6 = new Article();
						article6.setTitle("2015定向广州越野运动赛，嗨爆全城！");
						article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9D2kDmhZa5p2nNic5HsHWf8lia3RUh9ol3uVAhaxqD96TcbUuR9fOaXXZ0Fz0LuKsk065eFoUDNACtQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=213053315&idx=1&sn=ee338cb8d595e59c31081e3c6cb82fbe&scene=1&srcid=1019Avg3pItO9ZPQRz70Erc3&key=b410d3164f5f798ed6e0873aabad40aa6b3e0ae0a8397011dee21962683b98d5e5ced8dfaf72cf5b3a686c3822c469db&ascene=1&u");
						articleList.add(article6);

						Article article7 = new Article();
						article7.setTitle("【互联英雄联盟】义结金兰之逗比事儿");
						article7.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9C3hw1wE0APxVNRJy9FPrRSUtqf5vXj3PMocqVD80tt2DAp5ZfmmOPZ7P0KibkL3kbnRzjLUbhXxCw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article7.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=212282123&idx=2&sn=857263d30978f74268aad1d6f371580e&scene=1&srcid=1019sCaJM4zZhgzFO9ctZ7O5&from=singlemessage&isappinstalled=0&key=b410d3164f5f798e746f3458f47b4187266ec553f202842627815fffb44786a187e2ef4");
						articleList.add(article7);

						Article article8 = new Article();
						article8.setTitle("哒哒运动：我们不请你吃饭，我们就请你流汗！");
						article8.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9DCJO5pxVR1JuxzWUp2WHtHvibZY83gyyrw4InZZMfJzVfemx5q9OQEL0J3QH3UicrxMuDakDsXDNvw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article8.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=211136661&idx=1&sn=84a7e4a981e7a3d0db96a607e455031f&scene=1&srcid=1019lBRZuRmDR3E8AyGMahph&key=b410d3164f5f798e8ef7e4d30367ee47414be6c9241eb1a88a736e437ea8446d13f5f55cf5de2f78ce12ab8ec58bbcad&ascene=1&u");
						articleList.add(article8);

						Article article9 = new Article();
						article9.setTitle("校园哒人封神榜——俊男靓女都在我碗里！");
						article9.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9BZlYNGod6uSoaaLdtX2CJfrUzjXtSnOH9x8GZ9QB8S85KWk7QSH6IOjdpdLiajyDoXgXbbiaWmicxSg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article9.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=210919895&idx=1&sn=d1938b15fd95ed2b7070301d97dc1745#rd");
						articleList.add(article9);

						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	private String sendVoteMessage(String fromUserName,
			NewsMessage newsMessage, List<Article> articleList)
			throws UnsupportedEncodingException {
		String respMessage;
		Article article = new Article();
		article.setTitle("哒哒运动校园运动哒人大赛");
		article.setPicUrl("http://vote.dadasports.cn/resources/images/v1.jpg");

		String redirect_url = url + "/user/queryPage";
		redirect_url = URLEncoder.encode(redirect_url, "utf-8");
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect

		article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri="
				+ redirect_url
				+ "&response_type=code&scope=snsapi_base&state="
				+ fromUserName + "#wechat_redirect");

		Article article2 = new Article();
		article2.setTitle("哒哒运动校园运动哒人大赛投票圆满结束");
		article2.setPicUrl("http://vote.dadasports.cn/resources/images/activity-result.png");
		article2.setUrl("http://vote.dadasports.cn/activityResult");

		Article article3 = new Article();
		article3.setTitle("哒哒运动校园运动哒人大赛规则");
		article3.setPicUrl("http://vote.dadasports.cn/resources/images/v4-s.jpg");
		article3.setUrl("http://vote.dadasports.cn/index");


		articleList.add(article);
		articleList.add(article2);
		articleList.add(article3);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		return respMessage;
	}
	
	private String sendLuckMessage(String fromUserName,
			NewsMessage newsMessage, List<Article> articleList)
			throws UnsupportedEncodingException {
		String respMessage;
		Article article = new Article();
		article.setTitle("达人邦暨英雄联盟 万圣狂欢之夜");
		article.setPicUrl("http://vote.dadasports.cn/resources/app/img/tuwen.png?v=1.0");

		String redirect_url = url + "/luck/index";
		redirect_url = URLEncoder.encode(redirect_url, "utf-8");
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect

		article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri="
				+ redirect_url
				+ "&response_type=code&scope=snsapi_userinfo&state="
				+ fromUserName + "#wechat_redirect");


		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		return respMessage;
	}

	private  String sendLuckResultMessage(String fromUserName,
	NewsMessage newsMessage, List<Article> articleList)
			throws UnsupportedEncodingException {
		String respMessage;
		Article article = new Article();
		article.setTitle("达人邦暨英雄联盟 万圣狂欢之夜 中奖结果!");
		article.setPicUrl("http://vote.dadasports.cn/resources/app/img/tuwen.png?v=1.0");

		String redirect_url = url + "/luck/index";
		redirect_url = URLEncoder.encode(redirect_url, "utf-8");
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect

		article.setUrl("http://vote.dadasports.cn/luck/result");


		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		return respMessage;
	}

	private String sendFilmActivity(String fromUserName, NewsMessage newsMessage,
			List<Article> articleList) throws UnsupportedEncodingException {
		String respMessage;
		Article article = new Article();
		article.setTitle("晒运动宣言，看免费电影！");
		article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/wD8bnia5Pz9AT28PpdHRFzAY3Jeiba64sJ5ib32F9BetT3yGic7nepVRbHdzHEB2kcCibyfzuV7gPO4e98HTm9iaawNQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
		article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=213287393&idx=1&sn=8237819f47cc95a932e46602788e26e6#rd");
		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);
		return respMessage;
	}
}
