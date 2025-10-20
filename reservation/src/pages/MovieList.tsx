import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/MovieList.css";
import axios from "axios";

interface Movie {
  id: number;
  title: string;
  poster_path: string;
  release_date: string;
}

const MovieList = () => {
  const [movies, setMovies] = useState<Movie[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  const [selectedMovie, setSelectedMovie] = useState<Movie | null>(null); // 선택한 영화
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await axios.get(
          "https://api.themoviedb.org/3/movie/now_playing",
          {
            params: {
              api_key: "0f234c975f1d4f8a3f94619e3e9bce0f",
              language: "ko-KR",
              region: "KR",
            },
          }
        );
        setMovies(response.data.results);
      } catch (err) {
        setError("영화 정보를 불러오는 데 실패했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchMovies();
  }, []);

  if (loading) return <div>로딩 중...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="movie-list">
      <h1>🎬 상영 예정 영화</h1>
      <br/>
      <div className="movies">
        {movies.map((movie) => (
          <div
            className="movie"
            key={movie.id}
            onClick={() => setSelectedMovie(movie)}
            style={{ cursor: "pointer" }}
          >
            <img
              src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`}
              alt={movie.title}
              className="movie-poster"
            />
            <h3>{movie.title}</h3>
            <p>{movie.release_date}</p>
          </div>
        ))}
      </div>

      {/* 모달 */}
      {selectedMovie && (
        <div className="modal-overlay">
          <div className="modal">
            <h2>예매 확인</h2>
            <img
              src={`https://image.tmdb.org/t/p/w300${selectedMovie.poster_path}`}
              alt={selectedMovie.title}
              className="modal-poster"
            />
            <h3>{selectedMovie.title}</h3>
            <div className="modal-buttons">
          <button
            className="confirm"
            onClick={() =>
              navigate(`/SeatsReservation/${selectedMovie.id}`, {
                state: { title: selectedMovie.title }, // 제목도 같이 전달
              })
            }
          >
                예매하기
              </button>
              <button className="cancel" onClick={() => setSelectedMovie(null)}>
                취소
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default MovieList;
