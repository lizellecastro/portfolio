import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  Button,
  TouchableOpacity,
  Image,
  StyleSheet,
  FlatList,
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { AntDesign } from '@expo/vector-icons';

const Stack = createNativeStackNavigator();

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#e6e6fa', // light purple color
  },
  image: {
    width: 200,
    height: 200,
    marginBottom: 20,
  },
  title: {
    fontSize: 36,
    fontWeight: 'bold',
    marginBottom: 20,
    color: 'blue',
    textAlign: 'center',
    textShadowColor: 'rgba(0, 0, 0, 0.75)',
    textShadowOffset: { width: -1, height: 1 },
    textShadowRadius: 10,
    fontFamily: 'Comic Sans MS',
  },
  button: {
    backgroundColor: 'green',
    padding: 20,
    borderRadius: 50,
    width: 100,
    height: 100,
    justifyContent: 'center',
    alignItems: 'center',
  },
  rectangularButton: {
    backgroundColor: 'purple',
    paddingVertical: 15,
    paddingHorizontal: 40,
    borderRadius: 10,
    alignItems: 'center',
  },
  startButton: {
    padding: 20,
    borderRadius: 8,
    marginTop: 10,
  },
  startButtonGreen: {
    backgroundColor: 'green', // 'Start New Round' button is green
  },
  startButtonBlue: {
    backgroundColor: 'blue', // 'Go to Scoreboard' button is blue
  },
  startButtonText: {
    color: 'white',
    fontSize: 18,
    fontFamily: 'Comic Sans MS',
  },
  playScreenText: {
    fontSize: 18,
    color: '#4169e1', // Dark purple text color
    fontWeight: 'bold',  // Make the text bold
    fontFamily: 'Comic Sans MS',
  },
  gameBoard: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    marginTop: 20,
    alignItems: 'center',  // Center the grid horizontally
    justifyContent: 'center',  // Center the grid vertically
  },
  cell: {
    width: 100,
    height: 100,
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 2,
    borderColor: '#9932cc',
    borderBottomWidth: 4, // Increase the bottom border width
    borderRightWidth: 4,  // Increase the right border width
    borderLeftWidth: 4,
    borderTopWidth: 4,
  },
  cellText: {
    fontSize: 40,
    color: 'darkpurple',  // Dark purple text color
    fontWeight: 'bold',  // Make the text bold
    fontFamily: 'Comic Sans MS',
  },
  winnerText: {
    marginTop: 20,
    fontSize: 24,
    fontWeight: 'bold',
    color: 'darkpurple',  // Dark purple text color
    fontFamily: 'Comic Sans MS',
  },
  scoreboardContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#e6e6fa', // light purple color
  },
  scoreboardHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '80%',
    marginBottom: 20,
    backgroundColor: 'purple',
    padding: 10,
    borderRadius: 8,
  },
  scoreboardRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '80%',
    marginBottom: 10,
    borderBottomWidth: 1,
    borderBottomColor: 'white',
    paddingVertical: 10,
    alignItems: 'center', // Center content vertically in the row
  },
  scoreboardCell: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center', // Center content horizontally in the cell
  },
  scoreboardHeaderText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center', // Center the text in the cell header
    paddingRight: 18,
    fontFamily: 'Comic Sans MS',
  },
  scoreboardText: {
    fontSize: 16,
    textAlign: 'center', // Center the text in the cell data
    fontFamily: 'Comic Sans MS',
  },
  instructionsContainer: {
    borderWidth: 3,
    borderRadius: 10,
    borderColor: 'purple',
    padding: 20,
    marginBottom: 20,
  },
  instructionsTitle: {
    fontSize: 36,
    fontWeight: 'bold',
    marginBottom: 20,
    color: 'purple',
    textAlign: 'center',
    fontFamily: 'Comic Sans MS',
  },
  instructionsText: {
    fontSize: 18,
    marginBottom: 15,
    color: 'black',
    fontFamily: 'Comic Sans MS',
  },
});

const HomeScreen = ({ navigation }) => {

  return (
    <View style={styles.container}>
      <Image
        source={{
          uri:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdr9iL_AJnhO-EwVRbgGjLizNJ1XYbzj6OMQ&usqp=CAU',
        }}
        style={[styles.image, { borderRadius: 20 }]}
      />
      <Text style={styles.title}>
        Ultimate {'\n'}Tic-Tac-Toe
      </Text>
      <TouchableOpacity
        onPress={() => navigation.navigate('Play')}
        style={styles.button}
      >
        <AntDesign name="play" size={50} color="white" />
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() => navigation.navigate('Instructions')}
        style={[styles.rectangularButton, { marginTop: 20 }]}
      >
        <Text style={{ color: 'white', fontSize: 20 }}>Instructions</Text>
      </TouchableOpacity>
    </View>
  );
};

const InstructionsScreen = ({ navigation }) => {

  return (
    <View style={styles.container}>
      <View style={styles.instructionsContainer}>
        <Text style={styles.instructionsTitle}>
          Game Instructions
        </Text>
        <Text style={styles.instructionsText}>
          1. You will start off as the X player and immediately after placing your 
          X, the computer will place it's O.
        </Text>
        <Text style={styles.instructionsText}>
          2. Play against the computer for as long as you like and once you want to
          leave the game you can then click either the top left arrow to return
          back to the 'Home' screen or click on the 'Go to Scoreboard' button.
        </Text>
        <Text style={styles.instructionsText}>
          3. When you click on the 'Scoreboard' button, you will see the scores 
          updated as you being 'Player 1' and the Computer as 'Player 2' along with 
          the timestamp of completion.
        </Text>
        <Text style={styles.instructionsText}>
          4. Most importantly, have fun!
        </Text>
      </View>
    </View>
  );
};

const PlayScreen = ({ navigation }) => {
  const [isNavigated, setIsNavigated] = useState(false);
  const [gameBoard, setGameBoard] = useState(Array(9).fill(null));
  const [playerTurn, setPlayerTurn] = useState('X');
  const [winner, setWinner] = useState(null);
  const [playerScore, setPlayerScore] = useState(0);
  const [computerScore, setComputerScore] = useState(0);
  const [round, setRound] = useState(1);

  useEffect(() => {
    if ( playerTurn === 'O' && !winner) {
      const availableMoves = gameBoard.reduce((acc, cell, index) => {
        if (cell === null) {
          acc.push(index);
        }
        return acc;
      }, []);
      const randomMove =
        availableMoves[Math.floor(Math.random() * availableMoves.length)];
      makeMove(randomMove);
    }
  }, [isNavigated, gameBoard, playerTurn, winner]);

  const handleStart = () => {
    setIsNavigated(true);
    setGameBoard(Array(9).fill(null));
    setWinner(null);
    setPlayerTurn('X');
    setRound(1);
  };

  const makeMove = (index) => {
    if (!winner && gameBoard[index] === null) {
      const newBoard = gameBoard.slice();
      newBoard[index] = playerTurn;
      setGameBoard(newBoard);
      checkWinner(newBoard, playerTurn);

      setPlayerTurn(playerTurn === 'X' ? 'O' : 'X');
    }
  };

  const checkWinner = (board, currentPlayer) => {
    const winConditions = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];

    for (let i = 0; i < winConditions.length; i++) {
      const [a, b, c] = winConditions[i];
      if (board[a] && board[a] === board[b] && board[a] === board[c]) {
        setWinner(currentPlayer);
        if (currentPlayer === 'X') {
          setPlayerScore(playerScore + 1);
        } else {
          setComputerScore(computerScore + 1);
        }
        setRound(round + 1);
        return;
      }
    }

    if (board.every((cell) => cell !== null)) {
      setWinner('Draw');
      setRound(round + 1);
    }
  };

  const resetGame = () => {
    setGameBoard(Array(9).fill(null));
    setWinner(null);
    setPlayerTurn('X');
  };

  const goToScoreboard = () => {
    const timestamp = new Date().toLocaleString();
    navigation.navigate('Scoreboard', {
      updateScoreboard: true,
      playerScore,
      computerScore,
      timestamp,
    });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.playScreenText}>Current Turn: {winner ? 'Game Over' : playerTurn}</Text>
      <View style={styles.gameBoard}>
        {gameBoard.map((cell, index) => (
          <TouchableOpacity
            key={index}
            style={styles.cell}
            onPress={() => makeMove(index)}
            disabled={cell !== null || winner !== null}
          >
            <Text style={styles.cellText}>{cell}</Text>
          </TouchableOpacity>
        ))}
      </View>
      <Text style={styles.winnerText}>
        {winner ? (winner === 'Draw' ? "It's a Draw!" : `Winner: ${winner}`) : ''}
      </Text>
      <Text style={styles.playScreenText}>Player: {playerScore} Computer: {computerScore}</Text>
      {round > 1 && round <= 3 && (
        <TouchableOpacity
          style={[styles.startButton, styles.startButtonGreen]} // Use the green style
          onPress={resetGame}
        >
          <Text style={styles.startButtonText}>Start New Round</Text>
        </TouchableOpacity>
      )}
      {round > 3 && (
        <TouchableOpacity
          style={[styles.startButton, styles.startButtonGreen]} // Use the green style
          onPress={handleStart}
        >
          <Text style={styles.startButtonText}>Start New Game</Text>
        </TouchableOpacity>
      )}
      <TouchableOpacity
        style={[styles.startButton, styles.startButtonBlue]} // Use the blue style
        onPress={goToScoreboard}
      >
        <Text style={styles.startButtonText}>Go to Scoreboard</Text>
      </TouchableOpacity>
    </View>
  );
};

const ScoreboardScreen = ({ route, navigation }) => {
  const [scoreboard, setScoreboard] = useState([
    { player: 'Player 1', score: 0, timestamp: '' },
    { player: 'Player 2', score: 0, timestamp: '' },
  ]);

  const updateScoreboard = route?.params?.updateScoreboard;

  useEffect(() => {
    if (updateScoreboard) {
      const { playerScore, computerScore, timestamp } = route.params;
      const updatedScoreboard = [...scoreboard];

      // Update scores based on the player
      updatedScoreboard[0].score = playerScore;
      updatedScoreboard[1].score = computerScore;

      // Update timestamp
      updatedScoreboard.forEach((item) => {
        item.timestamp = timestamp;
      });

      setScoreboard(updatedScoreboard);
    }
  }, [updateScoreboard, route.params, scoreboard]);

  return (
    <View style={styles.scoreboardContainer}>
      <Text style={{ fontSize: 24, marginBottom: 30, marginTop: 30, fontFamily: 'Comic Sans MS' }}>Scoreboard</Text>
      <View style={styles.scoreboardHeader}>
        <View style={styles.scoreboardCell}>
          <Text style={styles.scoreboardHeaderText}>Player</Text>
        </View>
        <View style={styles.scoreboardCell}>
          <Text style={styles.scoreboardHeaderText}>Score</Text>
        </View>
        <View style={styles.scoreboardCell}>
          <Text style={styles.scoreboardHeaderText}>Timestamp</Text>
        </View>
      </View>
      <FlatList
        data={scoreboard}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <View style={styles.scoreboardRow}>
            <View style={styles.scoreboardCell}>
              <Text style={styles.scoreboardText}>{item.player}</Text>
            </View>
            <View style={styles.scoreboardCell}>
              <Text style={styles.scoreboardText}>{item.score}</Text>
            </View>
            <View style={styles.scoreboardCell}>
              <Text style={styles.scoreboardText}>{item.timestamp}</Text>
            </View>
          </View>
        )}
      />
      <Image
        source={{
          uri:
            'https://media.istockphoto.com/id/907642936/vector/sports-cup-emoj-icon-with-the-smiling-mug-in-flat-style-a-vector.jpg?s=612x612&w=0&k=20&c=lc562ZxpS-fnksR0u8TZfViIxGtMDOJKHumJGz8H7Ks=',
        }}
        style={{ width: 200, height: 200, marginTop: 20, marginBottom:120, borderRadius: 20, alignItems: 'center' }}
      />
      <TouchableOpacity
        style={[styles.rectangularButton, {borderRadius: 20}]}
        onPress={() => navigation.navigate('Home')}
      >
        <Text style={{ color: 'white', fontSize: 18 }}>Go back to Home</Text>
      </TouchableOpacity>
    </View>
  );
};

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Play" component={PlayScreen} />
        <Stack.Screen name="Scoreboard" component={ScoreboardScreen} />
        <Stack.Screen name="Instructions" component={InstructionsScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
