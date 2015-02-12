--\i create_tables.sql

DROP TABLE nfl.players CASCADE;
DROP TABLE nfl.teams CASCADE;
DROP TABLE nfl.games CASCADE;
DROP TABLE nfl.live_games CASCADE;
DROP TABLE nfl.team_stats CASCADE;
DROP TABLE nfl.offense CASCADE;
DROP TABLE nfl.defense CASCADE;

CREATE TABLE nfl.teams(
	team_code VARCHAR(3) PRIMARY KEY,
	team_city VARCHAR(30),
	team_nickname VARCHAR(30),
	division VARCHAR(30)	
);

CREATE TABLE nfl.players(
	player_id VARCHAR(10) PRIMARY KEY,
	first_name VARCHAR(20),
	last_name VARCHAR(50)
);


CREATE TABLE nfl.games(
	game_id SERIAL PRIMARY KEY,
	game_date DATE,
	week VARCHAR(20),
	away_team_code VARCHAR(3) REFERENCES nfl.teams,
	home_team_code VARCHAR(3) REFERENCES nfl.teams,
	q1_away INTEGER,
	q2_away INTEGER,
	q3_away INTEGER,
	q4_away INTEGER,
	ot_away INTEGER,
	q1_home INTEGER,
	q2_home INTEGER,
	q3_home INTEGER,
	q4_home INTEGER,
	ot_home INTEGER,
	away_score INTEGER,
	home_score INTEGER
);

CREATE TABLE nfl.team_stats(
	game_id INTEGER REFERENCES nfl.games,
	team_code VARCHAR(3),
	first_downs INTEGER,
	fumbles_lost INTEGER,
	penalties INTEGER,
	penalty_yds INTEGER
);

CREATE TABLE nfl.offense(
	game_id INTEGER REFERENCES nfl.games,
	player_id VARCHAR(10) REFERENCES nfl.players,
	team_code VARCHAR(3),
	pass_cmp INTEGER,
	pass_att INTEGER,
	pass_yds INTEGER,
	pass_tds INTEGER,
	ints INTEGER,
	long_pass INTEGER,
	rush_att INTEGER,
	rush_yds INTEGER,
	rush_tds INTEGER,
	long_rush INTEGER,
	receptions INTEGER,
	rec_yds INTEGER,
	rec_tds INTEGER,
	long_rec INTEGER
);

CREATE TABLE nfl.defense(
	game_id INTEGER REFERENCES nfl.games,
	player_id VARCHAR(10) REFERENCES nfl.players,
	team_code VARCHAR(3),
	sacks DOUBLE PRECISION,
	ints INTEGER,
	int_yds INTEGER,
	int_tds INTEGER,
	long_int_yds INTEGER,
	fumb_rec INTEGER,
	fumb_yds INTEGER,
	fumb_tds INTEGER,	
	kick_ret INTEGER,
	ret_yds INTEGER,
	ret_tds INTEGER,
	long_ret INTEGER,
	punt_ret INTEGER,
	punt_yds INTEGER,
	punt_tds INTEGER,
	long_punt INTEGER
);

CREATE TABLE nfl.live_games(
	game_id INTEGER PRIMARY KEY,
	away_team_code VARCHAR(3),
	home_team_code VARCHAR(3),
	away_score INTEGER,
	home_score INTEGER
);

CREATE OR REPLACE FUNCTION GET_TOTALS() RETURNS TRIGGER
AS $$
DECLARE 
BEGIN
	NEW.away_score := NEW.q1_away + NEW.q2_away + NEW.q3_away + NEW.q4_away + NEW.ot_away;
	NEW.home_score := NEW.q1_home + NEW.q2_home + NEW.q3_home + NEW.q4_home + NEW.ot_home;

RETURN NEW;
END;
$$
language 'plpgsql';

CREATE TRIGGER get_game_totals
BEFORE INSERT OR UPDATE ON nfl.games
FOR EACH ROW EXECUTE PROCEDURE GET_TOTALS();


/*
CREATE TABLE nfl.player_team(
	team_id INTEGER,
	player_id INTEGER,
	position VARCHAR(3),
	CONSTRAINT pk_player_team PRIMARY KEY (team_id, player_id)
	
);



CREATE TABLE nfl.kicking_stats(

);

CREATE TABLE nfl.return_stats(

);*/