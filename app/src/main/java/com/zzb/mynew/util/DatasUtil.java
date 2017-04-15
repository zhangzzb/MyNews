package com.zzb.mynew.util;

import java.util.Random;

/**
 * des:假数据
 * Created by xsf
 * on 2016.07.11:14
 */
public class DatasUtil {
    /**
     * 图片
     */
    private static String[]Urls={"http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg", "http://h.hiphotos.baidu.com/image/pic/item/30adcbef76094b36db47d2e4a1cc7cd98c109de6.jpg","http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c27dc0dcdd52a6059242da6cc.jpg"
            ,"http://c.hiphotos.baidu.com/image/h%3D200/sign=d21f63f99d3df8dcb93d8891fd1072bf/78310a55b319ebc415951b978026cffc1f1716ca.jpg","http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg"
    ,"http://e.hiphotos.baidu.com/image/h%3D200/sign=7683f02abc096b639e1959503c328733/203fb80e7bec54e74a142d1bbb389b504fc26a3e.jpg",
            "http://img2.imgtn.bdimg.com/it/u=339476892,3696257834&fm=15&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=504496373,618042034&fm=15&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3597711708,3274029571&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3552753049,4174407950&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2210965130,3939028825&fm=15&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2075634502,557639518&fm=15&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=472834131,361620142&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1430347634,734937924&fm=15&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3725302317,224415526&fm=15&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3201886670,2044632765&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1840790974,3012825533&fm=15&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3180248538,1538577500&fm=15&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=441180467,2267686847&fm=15&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=4201172200,2608024653&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1900532298,3812462235&fm=15&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1481939063,2939894547&fm=15&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=635910431,3487163175&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1114752089,1719394746&fm=15&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1275557958,2272590510&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3811121209,4179521072&fm=15&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3251552771,2615356985&fm=15&gp=0.jpg",
    };
    /**
     * 文字
     */
    private static String[]Strings= {"春天像一个步履轻盈的小姑娘。她携着神奇的花篮，把五彩的鲜花撒向山坡，撒向原野。她伴着欢快的溪流，把婉转的歌儿唱给青山，唱给牧童。秋天像一位技艺高超 的丹青手，她端起巨大的画板，把金黄的水粉洒向田野，洒向村庄。她举起神奇的画笔，，用斑斓的色彩点染树梢，点染山峦。",
            "大的东西变小，小的东西也会变大。“大”与“小”常常在某种条件下向对方转化。大的损失会因补救及时而变小，小的成绩会因继续努力而变大；“多”与“少”也是如此，多的问题会因及时求教而减少，少的知识会因不断积累而增多。",
            "菲尔丁说：“不好的书也像不好的朋友一样，可能会把你戕害。”这话没错。但不必为此走向另一极端，夸大书籍对人的品格的影响。更多的情况是：好人读了坏书仍是好人，坏人读了好书仍是坏人。",
            "尝试是乌云蔽日时能直上云霄的那种勇敢的鸟；尝试是大浪迭起时海上勇往直前的一叶扁舟。对于勇敢者，尝试是一条崭新的生活之路；对于懦弱者，尝试是一座铁筑的高墙。",
            "炽热的火伞高张在空中，热得河里的鱼不敢露出水面，鸟也不敢飞出山林，就是村中的狗也只是伸长舌头喘个不休。",
            "初夏时节，各色野花都开了，红的、紫的、粉的、黄的，像绣在一块绿色大地毯上的灿烂斑点；成群的蜜蜂在花从中忙碌着，吸着花蕊，辛勤地飞来飞去。",
    };
    private static String[] TIMEs={"2小时前","4小时前","5小时前","6小时前",
            "7小时前","3小时前","1小时前","9小时前",
            "2天前","12小时前","11小时前","10小时前",
            "3天前","4天前","5天前","6天前",
            };
    /**
     * 获取随机图片串
     * @param num
     * @return
     */
    public static String getRandomPhotoUrlString(int num) {
        StringBuilder sdbResult = new StringBuilder();
        if(num>0) {
            String[] photoUrls = new String[num>9?9:num];
            for (int i = 0; i< num ; i++) {
                if ( sdbResult.length() > 0 )
                {
                    sdbResult.append( ";" ).append( Urls[new Random().nextInt(Urls.length)] );
                }else{
                    sdbResult.append( Urls[new Random().nextInt(Urls.length)] );
                }

            }
        }
        return sdbResult.toString();
    }
    /**
     * 获取随机图片串
     * @return
     */
    public static String getRandomPhotoUrl() {
        return  Urls[new Random().nextInt(Urls.length)];
    }
    /**
     * 获取随机字符串
     * @return
     */
    public static String getRandomString() {
        return Strings[new Random().nextInt(Strings.length)];
    }
    public static String getRandomTime() {
        return TIMEs[new Random().nextInt(TIMEs.length)];
    }
}
