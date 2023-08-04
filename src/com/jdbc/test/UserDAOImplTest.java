package com.jdbc.test;

import java.util.ArrayList;

import com.jdbc.dao.impl.GeneralUserDAOImpl;
import com.jdbc.dao.impl.ManagerDAOImpl;
import com.jdbc.vo.GeneralUser;
import com.jdbc.vo.Manager;
import com.jdbc.vo.Movie;
import com.jdbc.vo.Review;
import com.jdbc.vo.User;

import config.ServerInfo;

public class UserDAOImplTest {

	public static void main(String[] args) throws Exception{
		GeneralUserDAOImpl gdao = GeneralUserDAOImpl.getInstance();
	    ManagerDAOImpl mdao = ManagerDAOImpl.getInstance();
	    
//	    /* ###### 사용자 CRUD ###### */
//
//        /* ## 회원가입 ## */
//
//        /* case 1. 관리자 회원가입 */
//
//        mdao.addUser(new Manager("지은","재무부서") );
//
//        mdao.addUser(new Manager("다은","경영부서") );
//
//        /* case 2. 일반 사용자 회원가입 */
//
//        gdao.addUser(new GeneralUser("채영","채영잉") );
//
//        gdao.addUser(new GeneralUser("주원","주원잉") );
//
//        //
//
//        /* ## 정보 업데이트 ## */
//
//        /* case 1. 관리자 부서 업데이트 */
//
//        mdao.updateUser(new Manager("다은","개발부서"));
//
//        /* case 2. 일반 사용자 닉네임 업데이트 */
//
//        gdao.updateUser(new GeneralUser("주원","변경후"));
//
//        /* ## 탈퇴 ## */
//
//        mdao.deleteUser("지은");
//
//        /* ###### 영화 CRUD ###### */
//
//        /* ## 영화 추가 ## */
//
//        mdao.addMovie(1,new Movie("TEST","공포", Movie.convertToDate("2023-07-02"),"디즈니",8));
//
//        /* ## 영화 수정 ## */
//
//        mdao.updateMovie(1, new
//        Movie("TEST","로맨스",Movie.convertToDate("2023-07-03"),"디즈니",10));

//        /* ## 영화 삭제 ## */
//
//        mdao.deleteMovie(1, new
//        Movie("TEST","로맨스",Movie.convertToDate("2023-07-03"),"디즈니",10));
//
//        /* ###### 사용자 조회 (관리자 권한) ###### */
//
//        mdao.getAllUsers(1);
//	    
//
//	        //모든 영화 리뷰를 출력
//	        System.out.println("\n=======모든 영화 리뷰를 출력합니다=======\n");
//	        ArrayList<Review> allReviews = gdao.getAllReviews();
//	        for(Review rl : allReviews) {
//	            System.out.println(rl);
//	        }
//	
//	        //특정 평점 영화 리뷰를 출력
//	        System.out.println("\n=======특정 평점의 리뷰를 출력합니다=======\n");
//	        ArrayList<Review> rateReviews = gdao.getAllReviewsByRate(10);
//	        for(Review rl : rateReviews) {
//	            System.out.println(rl);
//	        }
//	
//	        //특정 영화 리뷰 출력
//	        System.out.println("\n=======특정 영화의 리뷰를 출력합니다=======\n");
//	        ArrayList<Review> movieReviews = gdao.getAllReviewsByMovie("엘리멘탈");
//	        for(Review rl : movieReviews) {
//	            System.out.println(rl);
//	        }
//	
//	        //영화 목록 전체 출력
//	        System.out.println("\n=======영화 목록을 출력합니다=======\n");
//	        ArrayList<Movie> allMovies = gdao.getAllMovies();
//	        for(Movie ml : allMovies) {
//	            System.out.println(ml);
//	        }
//	
//	        //영화 장르별 전체 출력
//	        System.out.println("\n=======특정 장르 영화를 출력합니다=======\n");
//	        ArrayList<Movie> genreMovies = gdao.getAllMoviesByGenre("코미디");
//	        for(Movie ml : genreMovies) {
//	            System.out.println(ml);
//	        }
//	    
	    
	    
	    
		// 리뷰 CRUD
		//gdao.addReview(new Review("kd1284", "엘리멘탈", "정말 재밌다..", 10));
		//gdao.addReview(new Review("2oo1", "엘리멘탈", "감동~", 10));
	    //gdao.addReview(new Review("mellykim", "엘리멘탈", "그냥...", 8));
	    //gdao.addReview(new Review("mellykim", "바비", "good", 8));
	    //gdao.addReview(new Review("lcy923", "더문", "good~~~~~~", 10));
		//gdao.addReview(new Review("je123", "미션임파서블7", "그냥...", 5 ));
		gdao.deleteReview(new Review("kd1284", "엘리멘탈", "정말 재밌다..", 10));
		//gdao.updateReview(new Review("kd1284", "여름날우리", "verygood", 10));
		
		
		
//		// 개봉일순으로 영화 정렬 test
//        ArrayList<Movie> movieList = gdao.getAllMoviesSortByReleasedDate();
//        System.out.println("영화 제목" + "\t\t" + "개봉일" + "\t\t" + "평점");
//        System.out.println("=======================================");
//        for (Movie m : movieList)
//            System.out.printf("%-10s\t%s\t%d%n", m.getTitle(), m.getReleaseDate(), m.getTotalRate());
//        System.out.println();
//
//        // 리뷰 많이 작성한순으로 사용자 닉네임 & 작성한 리뷰 개수 출력 test
//        ArrayList<User> userList = gdao.getAllUsersSortByReviewCount();
//        System.out.println("닉네임" + "\t" + "작성한 리뷰 수");
//        System.out.println("====================");
//        for (User u1 : userList)
//            System.out.println(((GeneralUser) u1).getNickname() + "\t" + ((GeneralUser) u1).getNumOfReviews());
//        System.out.println();
////
        // 평점별로 영화 정렬 test
//        ArrayList<Movie> movieList1 = gdao.getAllMoviesSortByRate();
//        System.out.println("영화 제목" + "\t\t" + "개봉일" + "\t\t" + "평점");
//        System.out.println("=======================================");
//        for (Movie m : movieList1)
//            System.out.printf("%-10s\t%s\t%d%n", m.getTitle(), m.getReleaseDate(), m.getTotalRate());
//        System.out.println();
//
//        // 내가 작성한 리뷰 가져오기 test
//        ArrayList<Review> reviewList = gdao.getPortfolio("2oo1");
//        System.out.println("영화 제목" + "\t\t" + "리뷰 내용" + "\t\t" + "평점");
//        System.out.println("=======================================");
//        for (Review r : reviewList)
//            System.out.printf("%-10s\t%-10s\t%d%n", r.getTitle(), r.getContent(), r.getRate());
	}
	static { 
		try {  
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading Success...");
			
		} catch(ClassNotFoundException e) {
			System.out.println("Driver Loading Fail...");
		} 
	}

}
