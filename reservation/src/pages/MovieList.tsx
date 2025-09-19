// src/components/MovieList.tsx
import React, { useEffect, useState } from 'react';
import '../styles/MovieList.css';
import axios from 'axios';

const MovieList = () => {
  const [movies, setMovies] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await axios.get(
          'https://api.themoviedb.org/3/movie/now_playing',
          {
            params: {
              api_key: '0f234c975f1d4f8a3f94619e3e9bce0f',
              language: 'ko-KR',
              region: 'KR',
            },
          }
        );
        setMovies(response.data.results);
      } catch (err) {
        setError('영화 정보를 불러오는 데 실패했습니다.');
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
      <h1>🎬 현재 상영 중인 영화</h1>
      <div className="movies">
        {movies.map((movie) => (
          <div className="movie" key={movie.id}>
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
    </div>
  );
};

export default MovieList;
