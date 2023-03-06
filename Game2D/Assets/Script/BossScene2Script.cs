using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossScene2Script : MonoBehaviour
{
     public  float right, left;
    public new Rigidbody2D rigid;
    public float speed;
    public  bool isRight;
    public GameObject fireboss;
     public GameObject fireboss1;
    private float timeSpawm;
    private float time;
    private float bloodBoss;
    // Start is called before the first frame update
    void Start()
    {
        timeSpawm = 0.2f;
        time = timeSpawm;
        bloodBoss = 100;
    }

    // Update is called once per frame
    void Update()
    {
        float positionX = transform.position.x;
        if(positionX < left ){
            // quay qua phải
                isRight = true;
                Vector2 scale = transform.localScale;
                scale.x *= scale.x  < 0 ? 1 : -1;
                transform.localScale = scale;
                }
        else if(positionX > right){
            // quay qua trái
            isRight = false;
             Vector2 scale = transform.localScale;
            scale.x *= scale.x  < 0 ? -1 : 1;
            transform.localScale = scale;
    }
        Vector3  vector3;
    if(isRight){
        vector3 = new Vector3(1, 0, 0);
    }else{
        vector3 = new Vector3(-1, 0, 0);
    }
    transform.Translate(vector3 * speed * Time.deltaTime);
    time -= Time.deltaTime;
    if(time <= 0){
        time = timeSpawm;
        GameObject fb = Instantiate(fireboss);
        fb.transform.position = new Vector2(
            transform.position.x + (isRight ? 0.8f : -0.8f),
            transform.position.y
        );
        fb.GetComponent<FirebossScene2Script>().setSpeed( isRight ? 10 : -10);
        // GameObject fb1 = Instantiate(fireboss1);
        // fb1.transform.position = new Vector2(
        //     transform.position.x + (isRight ? 0.8f : -0.8f),
        //     transform.position.y
        // );
        // fb1.GetComponent<FirebossScript>().setSpeed( isRight ? 10 : -10);
    }
    }
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.CompareTag("fire")){
            if(bloodBoss > 0){
                bloodBoss -= 20;
            }else{
                Destroy(gameObject,1);
            }
        }
    }
}
