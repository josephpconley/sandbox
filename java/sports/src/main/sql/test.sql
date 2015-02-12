SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = O.player_id) AS player_name,
	   (SUM(pass_tds)) AS tds
FROM nfl.offense O
GROUP BY player_id
ORDER BY tds DESC;

/*
SELECT rec_yds, G.game_date
FROM nfl.offense O
	INNER JOIN nfl.games G
	ON O.game_id = G.game_id 
WHERE player_id = 'LewiGr01'
ORDER BY game_date;


SELECT SUM(rec_yds), date_part('year',G.game_date) AS year
FROM nfl.offense O
	INNER JOIN nfl.games G
	ON O.game_id = G.game_id 
WHERE player_id = 'LewiGr01'
GROUP BY year
ORDER BY year;

SELECT game_date, away_team_code, home_team_code, away_score, home_score
FROM nfl.games
WHERE away_score = 
	(SELECT MAX(score)
	FROM (SELECT away_score AS score
		FROM nfl.games
		WHERE away_team_code = 'PHI'
		UNION
		SELECT home_score AS score
		FROM nfl.games
		WHERE home_team_code = 'PHI') AS T)
AND away_team_code = 'PHI'
OR home_score = 
	(SELECT MAX(score)
	FROM (SELECT away_score AS score
		FROM nfl.games
		WHERE away_team_code = 'PHI'
		UNION
		SELECT home_score AS score
		FROM nfl.games
		WHERE home_team_code = 'PHI') AS T)
AND home_team_code = 'PHI'
;

--passing yds leaders
SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = O.player_id) AS player_name,
	   SUM(pass_yds) as total_yds
FROM nfl.offense O
WHERE game_id IN(SELECT game_id
			FROM nfl.games
			WHERE date_part('year',game_date) = 2009)
AND player_id IN (SELECT player_id
			FROM nfl.offense
			WHERE pass_yds > 0)
GROUP BY player_id
ORDER BY total_yds DESC;

--rushing yds leaders
SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = O.player_id) AS player_name,
	   SUM(rush_yds) as total_yds
FROM nfl.offense O
WHERE game_id IN(SELECT game_id
			FROM nfl.games
			WHERE date_part('year',game_date) = 2009)
AND player_id IN (SELECT player_id
			FROM nfl.offense
			WHERE rush_yds > 0)
GROUP BY player_id
ORDER BY total_yds DESC;

--rushing breakdown for player
SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = O.player_id) AS player_name,
	   rush_yds,
	   team_code
FROM nfl.offense O
WHERE game_id IN(SELECT game_id
			FROM nfl.games
			WHERE date_part('year',game_date) = 2009)
AND player_id IN (SELECT player_id
			FROM nfl.players
			WHERE first_name = 'Adrian'
			AND last_name = 'Peterson')
ORDER BY game_id;

--receiving
SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = O.player_id) AS player_name,
	   SUM(rec_yds) as total_yds
FROM nfl.offense O
WHERE game_id IN(SELECT game_id
			FROM nfl.games
			WHERE date_part('year',game_date) = 2009)
AND player_id IN (SELECT player_id
			FROM nfl.offense
			WHERE rec_yds > 0)
GROUP BY player_id
ORDER BY total_yds DESC;

--defense
SELECT (SELECT first_name || ' ' || last_name
		FROM nfl.players
		WHERE player_id = D.player_id) AS player_name,
	   SUM(sacks) as sacks
FROM nfl.defense D
WHERE game_id IN(SELECT game_id
			FROM nfl.games
			WHERE date_part('year',game_date) = 2009)
AND player_id IN (SELECT player_id
			FROM nfl.defense
			WHERE sacks > 0)
GROUP BY player_id
ORDER BY sacks DESC;
*/