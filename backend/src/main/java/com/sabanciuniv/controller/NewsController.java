package com.sabanciuniv.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Comments;
import com.sabanciuniv.model.Message;
import com.sabanciuniv.model.News;
import com.sabanciuniv.model.NewsCategory;
import com.sabanciuniv.repo.NewsRepo;
import com.sabanciuniv.repo.CatRepo;
import com.sabanciuniv.repo.CommentRepo;

@RestController
@RequestMapping("/newsapp")
public class NewsController {

	@Autowired private NewsRepo newsRepo;
	@Autowired private CommentRepo comRepo;
	@Autowired private CatRepo catRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@PostConstruct
	public void init() { //creating dummy data
		if(newsRepo.count() == 0) {
			logger.info("Database empty: Adding news...");
			
			//business news
			News new1 = new News("1", "1", "Better Call Saul!", "Hi, I’m Saul Goodman. "
					+ "Did you know that you have rights? The Constitution says you do. "
					+ "And so do I. I believe that until proven guilty, every man, woman, "
					+ "and child in this country is innocent. And that’s why I fight for you, "
					+ "Albuquerque! Better call Saul!", LocalDateTime.now(), "https://www.rappler.com"
							+ "/tachyon/2022/08/Screen-Shot-2022-08-16-at-3.54.21-PM.png");
			newsRepo.save(new1);
			
			News new2 = new News("2", "1", "Gustavo Fring, The owner of Los Pollos Hermanos died in an explosion.", "An explosion has been reported at the nursing home, Casa Tranquila. "
					+ "Following an explosion that rocked this facility earlier today, three people are reported dead "
					+ "including businessman Gustavo Fring. The fireball completely consumed the residents room and "
					+ "damaged common areas nearby. DEA has discovered that the resident was Hector Salamanca, "
					+ "a member of the Mexican cartel. Gustavo Fring’s connection to the resident and the cartel "
					+ "is still unknown.", LocalDateTime.now(), "https://static.onecms.io/wp-content/uploads"
							+ "/sites/6/2017/04/callsaul-2000.jpg");
			newsRepo.save(new2);
			
			//local news 
			News new3 = new News("3", "2", "Local Lawyer, Local Hero. Attorney climbs 65’ to save billboard workers life!", "Details unfold as witnesses share "
					+ "impressions of yesterday's gallant rescue by attorney James M. McGill. McGill scaled a construction vehicle and climbed "
					+ "onto a billboard catwalk, rescuing a worker, Robert Williams, in peril. McGill, though congratulated by local law enforcement and emergency response, denies heroic action.\n"
					+ "\"I saw a man in trouble and... well, instinct took over. I'm sure anyone else would have done the same thing,\" McGill said.", LocalDateTime.now(),
					"https://static.wikia.nocookie.net/breakingbad/images/b/b8"
					+ "/Better_Call_Saul_-_Hero_-_4.jpg/revision/latest?cb=20170130192913");
			newsRepo.save(new3);
			
			News new4 = new News("4", "2", "Heisenberg Found Dead", "The cook and kingpin who almost single-handedly flooded the Southwest with his signature blur methamphetamine drug was found dead Sunday night.\n"
					+ "He was found dead after a long battle with lung cancer. He was shot dead at a neo-Nazi compound with many others." , LocalDateTime.now(), "https://nofilmschool.com/sites/default/files/styles/article_wide/public/screen_shot_2018-11-07_at_11.04.11_am.png?itok=gAKmp1nD");
			newsRepo.save(new4);
			
			//entertainment news
			News new5 = new News("5", "3", "Mr Peanutbutter stole the D from the Hollywood sign.", "Mr Peanutbutter from Mr Peanutbutter’s house has confessed"
					+ " to stealing the D for his girlfriend Diane "
					+ "in what many are calling "
					+ "the most romantic gesture "
					+ "in the history of romance and gestures. "
					+ "We now await the L.A.P.D.'s move.", LocalDateTime.now(), "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSin5HW1EdNDH6Eh"
							+ "lujFNxPGOClwMhaGBube1E8GI1AJTaUVMils9KKKZXeVrNuIeMAvsM&usqp=CAU");
			newsRepo.save(new5);
			
			News new6 = new News("6", "3", "Bojack hates the Troops", "All Neal McNeal the Navy SEAL wanted was his favourite brand of breakfast muffins. As Neal got home from Afghanistan he went to the supermarket and called dibs on the last box. \n"
					+ "Bojack Horseman, from the 90s sitcom Horsin' Around, refused to respect his dibs.", LocalDateTime.now(), "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/d0ws68rvmxml9yvldalj.jpg");
			newsRepo.save(new6);
			
			//news categories
			NewsCategory ncat = new NewsCategory(1,"business");
			NewsCategory ncat2 = new NewsCategory(2,"local");
			NewsCategory ncat3 = new NewsCategory(3,"entertainment");
			
			catRepo.save(ncat);
			catRepo.save(ncat2);
			catRepo.save(ncat3);
			
			//comments
			Comments com1 = new Comments("1", "This guy is the best lawyer call him!", "Duck");
			Comments com2 = new Comments("2", "He was the best boss I've ever worked with", "Lyle");
			Comments com3 = new Comments("3", "Are you telling me that a man just happens to fall like that? No, he orchestrated it.", "Chuck");
			Comments com4 = new Comments("4", "He was an egomaniac, glad hes finally gone", "Ghost");
			Comments com5 = new Comments("5", "The day they make love a crime is the day I return my badge", "Officer");
			Comments com6 = new Comments("6", "I definitely had dibs", "navySeal35");
			
			comRepo.save(com1);
			comRepo.save(com2);
			comRepo.save(com3);
			comRepo.save(com4);
			comRepo.save(com5);
			comRepo.save(com6);
		}
	}
	
	
	@GetMapping("/getbycategoryid/{catId}")
	public Message<List<News>> NewsByCat(@PathVariable String catId) {
		Message<List<News>> msg = new Message<List<News>>();
		
		List<News> news = newsRepo.findByCategory(catId);
		
		if(news == null) {
			msg.setItems(null);
			msg.setServiceMessageCode("0");
		}
		else {
			msg.setItems(news);
			msg.setServiceMessageCode("1");
		}
		
		return msg;
	}
	
	@GetMapping("/getallnewscategories")
	public Message<List<NewsCategory>> GetCat() {
		Message<List<NewsCategory>> msg = new Message<List<NewsCategory>>();
		
		List<NewsCategory> cats = catRepo.findAll();
		
		if(cats == null) {
			msg.setItems(null);
			msg.setServiceMessageCode("0");
		}
		else {
			msg.setItems(cats);
			msg.setServiceMessageCode("1");
		}
		
		return msg;
	}
	
	@GetMapping("/getnewsbyid/{newsId}")
	public Message<News> NewsById(@PathVariable String newsId) {
		Message<News> msg = new Message<News>();
		
		News news = newsRepo.findById(newsId).orElse(null);
		
		if(news == null) {
			msg.setItems(null);
			msg.setServiceMessageCode("0");
		}
		else {
			
			NewsCategory cat = catRepo.findById(Integer.parseInt(news.getCategory())).orElse(null);
			news.setCategory(cat.getName());
			msg.setItems(news);
			msg.setServiceMessageCode("1");
		}
		return msg;
	}
	
	@GetMapping("/getcommentsbynewsid/{newsId}")
	public Message<List<Comments>> CommentByNewsId(@PathVariable String newsId) {
		Message<List<Comments>> msg = new Message<List<Comments>> ();
		
		List<Comments> com = comRepo.findByNid(newsId);
		
		if(com == null) {
			msg.setItems(null);
			msg.setServiceMessageCode("0");
		}
		else {
			msg.setItems(com);
			msg.setServiceMessageCode("1");
		}
		return msg;
	}
	
	@PostMapping("/savecomment")
	public Message<Comments> saveComment(@RequestBody Comments com){
		Message<Comments> msg = new Message<Comments>();
		//check if fields are null
		System.out.println(com.getText());
		System.out.println(com.getName());
		System.out.println(com.getNews_id());
		if(com.getName() == null || com.getText() == null || com.getNews_id() == null)
		{
			msg.setItems(null);
			msg.setServiceMessageCode("0");
		}
		else // no null we gotta check if the news exist
		{
			News news = newsRepo.findById(com.getNews_id()).orElse(null);
			
			if(news == null) {
				msg.setItems(null);
				msg.setServiceMessageCode("0");
			}
			else{
				msg.setItems(null);
				msg.setServiceMessageCode("1");
				Comments comToSave = new Comments(com.getNews_id(), com.getName(), com.getText());	
				comRepo.save(comToSave);
				msg.setItems(comToSave);
			}
		}
		return msg;
	}
}
