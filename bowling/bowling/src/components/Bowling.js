import Frame from "./Frame"

export default function Bowling() {
    const [frames, setFrames] = useState({});
    const [score, setScore] = useState(0);

    let framesToSave = [];
    for (var i = 0; i < 10; i++) {
        framesToSave.push({
            frame: i + 1,
            throwOne: 0,
            throwTwo: 0,
            score: 0
        })
    }

    return (
        <div className="bowling">
            <div className="frames">
                {framesToSave.map((frame, index) => {
                    return (
                        <Frame
                            key={index}
                            frame={frame.frame}
                            throwOne={frame.throwOne}
                            throwTwo={frame.throwTwo}
                            score={frame.score}
                            setScore={setScore}
                            setFrames={setFrames}
                        />
                    )
                })}
            </div>
        </div>
    )
}