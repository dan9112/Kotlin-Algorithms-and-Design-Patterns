package other

import org.junit.jupiter.api.Assertions

internal class FactorialAdvancedTest {

    private val factorial = FactorialAdvanced()

    @org.junit.jupiter.api.Test
    fun test_factorial_30() {
        val actual = factorial.compute(30)
        Assertions.assertEquals("265252859812191058636308480000000", actual.toString())
    }

    @org.junit.jupiter.api.Test
    fun test_factorial_60() {
        val actual = factorial.compute(60)
        Assertions.assertEquals("8320987112741390144276341183223364380754172606361245952449277696409600000000000000", actual.toString())
    }

    @org.junit.jupiter.api.Test
    fun test_factorial_90() {
        val expected = "93326215443944152681699238856266700490715968264381621468592963" +
                "895217599993229915608941463976156518286253697920827223758251185210916" +
                "864000000000000000000000000"

        val actual = factorial.compute(100)
        Assertions.assertEquals(expected, actual.toString())
    }

    @org.junit.jupiter.api.Test
    fun test_factorial_200() {
        val expected = "788657867364790503552363213932185062295135977687173263294742533244359449963403342920304" +
                "2840119846239041772121389196388302576427902426371050619266249528299311134628572707633172373969" +
                "8894392244562145166424025403329186413122742829485327752424240757390324032125740557956866022603" +
                "1904170324062351700858796178922222789623703897374720000000000000000000000000000000000000000000000000"

        val actual = factorial.compute(200)
        Assertions.assertEquals(expected, actual.toString())
    }

    @org.junit.jupiter.api.Test
    fun test_factorial_1000() {
        val expected = "40238726007709377354370243392300398571937486421071463254379991042993851" +
                "2398629020592044208486969404800479988610197196058631666872994808558901323829669" +
                "9445909974245040870737599188236277271887325197795059509952761208749754624970436" +
                "0141827809464649629105639388743788648733711918104582578364784997701247663288983" +
                "5955735432513185323958463075557409114262417474349347553428646576611667797396668" +
                "8202912073791438537195882498081268678383745597317461360853795345242215865932019" +
                "2809087829730843139284440328123155861103697680135730421616874760967587134831202" +
                "5478589320767169132448426236131412508780208000261683151027341827977704784635868" +
                "1701643650241536913982812648102130927612448963599287051149649754199093422215668" +
                "3257208082133318611681155361583654698404670897560290095053761647584772842188967" +
                "9646244945160765353408198901385442487984959953319101723355556602139450399736280" +
                "7501378376153071277619268490343526252000158885351473316117021039681759215109077" +
                "8801939317811419454525722386554146106289218796022383897147608850627686296714667" +
                "4697562911234082439208160153780889893964518263243671616762179168909779911903754" +
                "0312746222899880051954444142820121873617459926429565817466283029555702990243241" +
                "5318161721046583203678690611726015878352075151628422554026517048330422614397428" +
                "6933061690897968482590125458327168226458066526769958652682272807075781391858178" +
                "8896522081643483448259932660433676601769996128318607883861502794659551311565520" +
                "3609398818061213855860030143569452722420634463179746059468257310379008402443243" +
                "8465657245014402821885252470935190620929023136493273497565513958720559654228749" +
                "7740114133469627154228458623773875382304838656889764619273838149001407673104466" +
                "4025989949022222176590433990188601856652648506179970235619389701786004081188972" +
                "9918311021171229845901641921068884387121855646124960798722908519296819372388642" +
                "6148396573822911231250241866493531439701374285319266498753372189406942814341185" +
                "2015801412334482801505139969429015348307764456909907315243327828826986460278986" +
                "4321139083506217095002597389863554277196742822248757586765752344220207573630569" +
                "4988250879689281627538488633969099598262809561214509948717012445164612603790293" +
                "0912088908694202851064018215439945715680594187274899809425474217358240106367740" +
                "4595741785160829230135358081840096996372524230560855903700624271243416909004153" +
                "6901059339838357779394109700277534720000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                "000000000000000000000000000000000000000000000000"

        val actual = factorial.compute(1000)
        Assertions.assertEquals(expected, actual.toString())
    }
}